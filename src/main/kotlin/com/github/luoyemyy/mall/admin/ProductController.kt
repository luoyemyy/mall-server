@file:Suppress("unused")

package com.github.luoyemyy.mall.admin

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.entity.Product
import com.github.luoyemyy.mall.core.entity.ProductImage
import com.github.luoyemyy.mall.core.service.ProductService
import com.github.luoyemyy.mall.util.Role
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-产品"])
@RestController
@RequestMapping("admin/product")
class ProductController : BaseController() {

    @Autowired
    private lateinit var productService: ProductService

    /**
     *
     */
    @ApiOperation("产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "categoryId", value = "分类Id，全部分类为0", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])
    @RequestAdmin
    @GetMapping("list")
    fun list(categoryId: Long, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<ProductBean> {
        return listResponse(productService.getByCategoryAndPage(categoryId, page))
    }

    /**
     *
     */
    @ApiOperation("推荐产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "hotId", value = "热门id", required = true, dataTypeClass = Long::class)])
    @RequestAdmin
    @GetMapping("list/hot")
    fun hotProduct(hotId: Long): ListResponse<ProductBean> {
        return listResponse(productService.getByHot(hotId))
    }


    /**
     *
     */
    @ApiOperation("是否上架")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "online", value = "是否上架", required = true, dataTypeClass = Boolean::class)])
    @RequestAdmin
    @PostMapping("online")
    fun online(id: Long, online: Boolean): ApiResponse {
        return apiResponse(productService.online(id, online))
    }

    /**
     *
     */
    @ApiOperation("删除产品（管理员可用)")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])
    @RequestAdmin(true, Role.ADMIN_ID)
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(productService.delete(id))
    }

    /**
     *
     */
    @ApiOperation("产品详情")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])
    @RequestAdmin
    @GetMapping("get")
    fun get(id: Long): DataResponse<ProductDetail> {
        return dataResponse(productService.getDetail(id))
    }

    /**
     *
     */
    @ApiOperation("新增产品/编辑产品 id=0 新增 id>0 编辑")
    @RequestAdmin
    @PostMapping("aoe")
    fun add(@RequestBody detail: ProductDetail): ApiResponse {
        val product = Product().also {
            it.id = detail.id
            it.coverImage = detail.coverImage
            it.name = detail.name
            it.description = detail.description
            it.marketPrice = detail.marketPrice
            it.actualPrice = detail.actualPrice
            it.stock = detail.stock
            it.online = detail.online
        }
        val swipeImages = detail.swipeImages ?: throw MallException(Code.INVALID_PARAM, "缺少展示图")
        return apiResponse(productService.aoe(product, swipeImages, detail.descImages, detail.categoryIds))
    }

    /**
     *
     */
    @ApiOperation("产品排序")
    @RequestAdmin
    @PostMapping("sort")
    fun sortSave(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(productService.sort(sort))
    }

    /**
     *
     */
    @ApiOperation("产品模板图片")
    @RequestAdmin
    @GetMapping("template")
    fun template(): ListResponse<ProductTemplateImage> {
        return listResponse(productService.template())
    }

    /**
     *
     */
    @ApiOperation("产品模板图片新增和编辑")
    @RequestAdmin
    @PostMapping("template/aoe")
    fun templateAoe(@RequestBody images: List<ProductTemplateImage>): ApiResponse {
        return apiResponse(productService.templateAoe(images))
    }

}
