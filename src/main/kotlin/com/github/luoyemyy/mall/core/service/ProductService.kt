package com.github.luoyemyy.mall.core.service

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.*
import com.github.luoyemyy.mall.core.mapper.ProductCategoryMapper
import com.github.luoyemyy.mall.core.mapper.ProductImageMapper
import com.github.luoyemyy.mall.core.mapper.ProductMapper
import com.github.luoyemyy.mall.util.diff
import com.github.luoyemyy.mall.util.minus
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductService {

    @Autowired
    private lateinit var productDao: ProductDao
    @Autowired
    private lateinit var productMapper: ProductMapper
    @Autowired
    private lateinit var productImageMapper: ProductImageMapper
    @Autowired
    private lateinit var productCategoryMapper: ProductCategoryMapper
    @Autowired
    private lateinit var batchDao: BatchDao

    /**
     * @admin
     */
    fun getByHot(hotId: Long): List<ProductBean> {
        return productDao.selectByHot(hotId) ?: listOf()
    }

    /**
     * @admin
     */
    fun getByCategoryAndPage(categoryId: Long, page: Int): List<ProductBean> {
        return if (categoryId == 0L) {
            productDao.selectByPage(page.toPageStart()) ?: listOf()
        } else {
            productDao.selectByCategoryAndPage(categoryId, page.toPageStart()) ?: listOf()
        }
    }

    /**
     * @admin
     */
    fun online(productId: Long, online: Boolean): Boolean {
        return productMapper.updateByPrimaryKeySelective(Product().apply {
            this.id = productId
            this.online = online
        }) > 0
    }

    /**
     * @admin
     */
    fun delete(productId: Long): Boolean {
        return productMapper.updateByPrimaryKeySelective(Product().apply {
            id = productId
            status = 0
        }) > 0
    }

    /**
     * @admin
     * @param type 1 滑动展示图 2 描述图
     */
    private fun getImages(id: Long, type: Int): List<ProductImageBean>? {
        return productImageMapper.selectByExample(ProductImageExample().apply {
            createCriteria().andProductIdEqualTo(id).andTypeEqualTo(type)
            orderByClause = "sort asc"
        })?.map { ProductImageBean.fromProductImage(it) }
    }

    /**
     * @admin
     */
    fun getDetail(id: Long): ProductDetail {
        return productDao.get(id)?.apply {
            swipeImages = getImages(id, 1)
            descImages = getImages(id, 2)
            categoryIds = getCategoryByProduct(id)?.map { it.categoryId }
        } ?: throw MallException(Code.PRODUCT_NOT_EXIST)
    }

    /**
     * @admin
     */
    fun sort(sort: List<SortBean>): Boolean {
        if (sort.isEmpty()) return true
        return if (sort[0].typeId == 0L) {
            batchDao.updateProductSort(sort)
            true
        } else {
            batchDao.updateProductCategorySort(sort.map {
                ProductCategory().apply {
                    this.productId = it.id
                    this.categoryId = categoryId
                    this.sort = it.sort
                }
            })
            true
        }
    }

    /**
     * @admin
     */
    private fun nextSort(): Int {
        return (productDao.currentSort() ?: 0) + 1
    }

    /**
     * @admin
     */
    @Transactional
    fun aoe(product: Product, swipeImages: List<ProductImageBean>, descImages: List<ProductImageBean>?, categoryIds: List<Long>?): Boolean {

        if (product.id == null || product.id == 0L) {
            product.sort = nextSort()
            product.createTime = Date()
            product.status = 1
            if (productMapper.insert(product) == 0) {
                throw MallException(Code.FAILURE)
            }
        } else {
            if (productMapper.updateByPrimaryKeySelective(product) == 0) {
                throw MallException(Code.FAILURE)
            }
        }

        //保存分类
        updateCategory(product.id, categoryIds)

        //保存展示图
        updateProductImages(1, product.id, swipeImages)

        //保存描述图
        updateProductImages(2, product.id, descImages)

        return true
    }

    /**
     * @admin
     */
    private fun getCategoryByProduct(productId: Long): List<ProductCategory>? {
        return productCategoryMapper.selectByExample(ProductCategoryExample().apply {
            createCriteria().andProductIdEqualTo(productId)
        })
    }

    /**
     * @admin
     */
    private fun deleteProductCategory(productId: Long, categoryIds: List<Long>) {
        productCategoryMapper.deleteByExample(ProductCategoryExample().apply {
            createCriteria().andProductIdEqualTo(productId).andCategoryIdIn(categoryIds)
        })
    }

    /**
     * @admin
     */
    private fun nextProductCategorySort(categoryId: Long): Int {
        return (productDao.currentProductCategorySort(categoryId) ?: 0) + 1
    }

    /**
     * @admin
     */
    private fun updateCategory(productId: Long, newCategories: List<Long>?) {
        val (deleteCategoryIds, addCategoryIds) = diff(getCategoryByProduct(productId)?.map { it.categoryId }, newCategories)
        if (deleteCategoryIds.isNotEmpty()) {
            deleteProductCategory(productId, deleteCategoryIds)
        }
        addCategoryIds.map {
            ProductCategory().apply {
                this.productId = productId
                this.categoryId = it
                this.sort = nextProductCategorySort(it)
            }
        }.apply {
            if (isNotEmpty()) {
                batchDao.insertProductCategory(this)
            }
        }
    }

    /**
     * @admin
     */
    private fun deleteProductImage(productId: Long, imageIds: List<Long>) {
        productImageMapper.deleteByExample(ProductImageExample().apply {
            createCriteria().andProductIdEqualTo(productId).andIdIn(imageIds)
        })
    }

    /**
     * @admin
     */
    private fun updateProductImages(type: Int, productId: Long, newImages: List<ProductImageBean>?) {
        val deleteImages = getImages(productId, type).minus(newImages) { o, n -> o.id == n.id }
        if (deleteImages.isNotEmpty()) {
            deleteProductImage(productId, deleteImages.map { it.id })
        }
        if (!newImages.isNullOrEmpty()) {
            val sort = mutableListOf<SortBean>()
            val add = mutableListOf<ProductImage>()
            newImages.forEachIndexed { index, productImageBean ->
                if (productImageBean.id == 0L) {
                    add.add(ProductImage().apply {
                        this.productId = productId
                        this.image = productImageBean.image
                        this.type = type
                        this.sort = index + 1
                    })
                } else {
                    sort.add(SortBean().apply {
                        this.id = productImageBean.id
                        this.sort = index + 1
                    })
                }
            }
            if (add.isNotEmpty()) {
                batchDao.insertProductImage(add)
            }
            if (sort.isNotEmpty()) {
                batchDao.updateProductImageSort(sort)
            }
        }
    }

    fun template(type: Int): List<ProductTemplateImage> {
        return productImageMapper.selectByExample(ProductImageExample().apply {
            createCriteria().andProductIdEqualTo(0).andTypeEqualTo(type)
        })?.map { ProductTemplateImage.fromProductImage(it) } ?: listOf()
    }

    fun templateAoe(newImages: List<ProductTemplateImage>): Boolean {
        val type = newImages.firstOrNull()?.type?:0
        val deleteImages = template(type).minus(newImages) { o, n -> o.id == n.id }
        if (deleteImages.isNotEmpty()) {
            deleteProductImage(0, deleteImages.map { it.id })
        }
        if (!newImages.isNullOrEmpty()) {
            val sort = mutableListOf<SortBean>()
            val add = mutableListOf<ProductImage>()
            newImages.forEachIndexed { index, productImageBean ->
                if (productImageBean.id == 0L) {
                    add.add(ProductImage().apply {
                        this.productId = 0
                        this.image = productImageBean.image
                        this.type = productImageBean.type
                        this.sort = index + 1
                    })
                } else {
                    sort.add(SortBean().apply {
                        this.id = productImageBean.id
                        this.sort = index + 1
                    })
                }
            }
            if (add.isNotEmpty()) {
                batchDao.insertProductImage(add)
            }
            if (sort.isNotEmpty()) {
                batchDao.updateProductImageSort(sort)
            }
        }
        return true
    }
}