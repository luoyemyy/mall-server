package com.github.luoyemyy.mall.core.service.admin

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.controller.manager.response.ResponseManagerCategory
import com.github.luoyemyy.mall.core.service.admin.bean.SortBean
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.SortDao
import com.github.luoyemyy.mall.core.entity.Category
import com.github.luoyemyy.mall.core.entity.CategoryExample
import com.github.luoyemyy.mall.core.entity.ProductCategoryExample
import com.github.luoyemyy.mall.core.mapper.CategoryMapper
import com.github.luoyemyy.mall.core.mapper.ProductCategoryMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService {

    @Autowired
    private lateinit var categoryMapper: CategoryMapper
    @Autowired
    private lateinit var productCategoryMapper: ProductCategoryMapper
    @Autowired
    private lateinit var batchDao: BatchDao
    @Autowired
    private lateinit var sortDao: SortDao

    /**
     * @admin
     */
    fun allList(): List<ResponseManagerCategory>? {
        return categoryMapper.selectByExample(
                CategoryExample().apply {
                    orderByClause = "sort desc"
                })?.map { ResponseManagerCategory.map(it) }
    }

    /**
     * @admin
     */
    fun validList(): List<ResponseManagerCategory>? {
        return categoryMapper.selectByExample(
                CategoryExample().apply {
                    createCriteria().andStateEqualTo(1)
                    orderByClause = "sort desc"
                })?.map { ResponseManagerCategory.map(it) }
    }

    /**
     * @admin
     */
    private fun nextSort(): Int {
        return (sortDao.category() ?: 0) + 1
    }

    /**
     * @admin
     */
    @Transactional
    fun add(name: String): Boolean {
        val category = Category().also {
            it.name = name
            it.sort = nextSort()
            it.state = 1
        }
        if (categoryMapper.insert(category) > 0) {
            return true
        }
        throw AppException(AppCode.FAIL)
    }

    /**
     * @admin
     */
    @Transactional
    fun edit(id: Long, name: String): Boolean {
        return categoryMapper.updateByPrimaryKeySelective(Category().also {
            it.id = id
            it.name = name
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun state(id: Long, state: Int): Boolean {
        return categoryMapper.updateByPrimaryKeySelective(Category().also {
            it.id = id
            it.state = state
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun delete(id: Long): Boolean {
        return if (categoryMapper.deleteByPrimaryKey(id) > 0) {
            productCategoryMapper.deleteByExample(ProductCategoryExample().apply {
                createCriteria().andCategoryIdEqualTo(id)
            })
            true
        } else false
    }

    /**
     * @admin
     */
    @Transactional
    fun sort(sort: List<SortBean>): Boolean {
        return batchDao.updateCategorySort(sort)
    }
}