@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.*
import com.github.luoyemyy.mall.core.service.admin.ProductService
import com.github.luoyemyy.mall.core.service.admin.bean.ProductBean
import com.github.luoyemyy.mall.core.service.admin.bean.ProductDetail
import com.github.luoyemyy.mall.core.service.admin.bean.ProductTemplateImage
import com.github.luoyemyy.mall.core.service.admin.bean.SortBean
import com.github.luoyemyy.mall.core.entity.Product
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-产品"])
@RestController
@RequestMapping("admin/product")
class ManagerProductController06 : BaseController() {

    @Autowired
    private lateinit var productService: ProductService

    @AppApi(pathId = 10601)
    @ApiOperation("产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "categoryId", value = "分类Id，全部分类为0", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])

    @GetMapping("list")
    fun list(categoryId: Long, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<ProductBean> {
        return listResponse(productService.getByCategoryAndPage(categoryId, page))
    }

    @AppApi(pathId = 10602)
    @ApiOperation("推荐产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "hotId", value = "热门id", required = true, dataTypeClass = Long::class)])

    @GetMapping("list/hot")
    fun hotProduct(hotId: Long): ListResponse<ProductBean> {
        return listResponse(productService.getByHot(hotId))
    }


    @AppApi(pathId = 10603)
    @ApiOperation("是否上架")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "online", value = "是否上架", required = true, dataTypeClass = Boolean::class)])

    @PostMapping("online")
    fun online(id: Long, online: Boolean): ApiResponse {
        return apiResponse(productService.online(id, online))
    }

    @AppApi(pathId = 10604)
    @ApiOperation("删除产品（管理员可用)")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(productService.delete(id))
    }

    @AppApi(pathId = 10605)
    @ApiOperation("产品详情")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])

    @GetMapping("get")
    fun get(id: Long): DataResponse<ProductDetail> {
        return dataResponse(productService.getDetail(id))
    }

    @AppApi(pathId = 10606)
    @ApiOperation("新增产品/编辑产品 id=0 新增 id>0 编辑")

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
        val swipeImages = detail.swipeImages ?: throw AppException(AppCode.INVALID_PARAM, "缺少展示图")
        return apiResponse(productService.aoe(product, swipeImages, detail.descImages, detail.categoryIds))
    }

    @AppApi(pathId = 10607)
    @ApiOperation("产品排序")

    @PostMapping("sort")
    fun sortSave(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(productService.sort(sort))
    }

    @AppApi(pathId = 10608)
    @ApiOperation("产品模板图片")

    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "type", value = "模板类型3轮播图4描述图", required = true, dataTypeClass = Long::class)])
    @GetMapping("template")
    fun template(type: Int): ListResponse<ProductTemplateImage> {
        return listResponse(productService.template(type))
    }

    @AppApi(pathId = 10609)
    @ApiOperation("产品模板图片新增和编辑")

    @PostMapping("template/aoe")
    fun templateAoe(@RequestBody images: List<ProductTemplateImage>): ApiResponse {
        return apiResponse(productService.templateAoe(images))
    }

}
