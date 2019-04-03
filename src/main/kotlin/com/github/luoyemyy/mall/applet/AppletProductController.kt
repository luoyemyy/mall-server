@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.core.bean.ProductBean
import com.github.luoyemyy.mall.core.bean.ProductDetail
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.entity.Product
import com.github.luoyemyy.mall.core.service.ProductService
import com.github.luoyemyy.mall.core.service2.AppletProductService
import com.github.luoyemyy.mall.util.Role
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    @RequestApplet
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
    @RequestApplet
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
    @RequestApplet
    @GetMapping("get")
    fun get(id: Long): DataResponse<ProductDetail> {
        return dataResponse(appletProductService.getDetail(id))
    }

}
