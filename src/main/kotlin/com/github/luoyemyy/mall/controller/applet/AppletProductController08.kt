@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.admin.bean.ProductBean
import com.github.luoyemyy.mall.core.service.admin.bean.ProductDetail
import com.github.luoyemyy.mall.core.service.applet.AppletProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-产品"])
@RestController
@RequestMapping("applet/product")
class AppletProductController08 : BaseController() {

    @Autowired
    private lateinit var appletProductService: AppletProductService

    @AppApi(pathId = 20801, auth = false)
    @ApiOperation("产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "categoryId", value = "分类Id，全部分类为0", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])
    @PostMapping("list")
    fun list(categoryId: Long, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<ProductBean> {
        return listResponse(appletProductService.getByCategoryAndPage(categoryId, page))
    }

    @AppApi(pathId = 20802, auth = false)
    @ApiOperation("推荐产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "hotId", value = "热门id", required = true, dataTypeClass = Long::class)])
    @PostMapping("list/hot")
    fun hotProduct(hotId: Long): ListResponse<ProductBean> {
        return listResponse(appletProductService.getByHot(hotId))
    }

    @AppApi(pathId = 20803, auth = false)
    @ApiOperation("产品详情")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])
    @PostMapping("get")
    fun get(id: Long): DataResponse<ProductDetail> {
        return dataResponse(appletProductService.getDetail(id))
    }

}
