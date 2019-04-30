@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.DataResponse
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.dataResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.admin.bean.ProductBean
import com.github.luoyemyy.mall.core.admin.bean.ProductDetail
import com.github.luoyemyy.mall.core.applet.AppletProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-产品"])
@RestController
@RequestMapping("applet/product")
class AppletProductController : BaseController() {

    @Autowired
    private lateinit var appletProductService: AppletProductService

    /**
     *
     */
    @ApiOperation("产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "categoryId", value = "分类Id，全部分类为0", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])
    @RequestApplet(needLogin = false)
    @GetMapping("list")
    fun list(categoryId: Long, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<ProductBean> {
        return listResponse(appletProductService.getByCategoryAndPage(categoryId, page))
    }

    /**
     *
     */
    @ApiOperation("推荐产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "hotId", value = "热门id", required = true, dataTypeClass = Long::class)])
    @RequestApplet(needLogin = false)
    @GetMapping("list/hot")
    fun hotProduct(hotId: Long): ListResponse<ProductBean> {
        return listResponse(appletProductService.getByHot(hotId))
    }

    /**
     *
     */
    @ApiOperation("产品详情")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "产品Id", required = true, dataTypeClass = Long::class)])
    @RequestApplet(needLogin = false)
    @GetMapping("get")
    fun get(id: Long): DataResponse<ProductDetail> {
        return dataResponse(appletProductService.getDetail(id))
    }

}
