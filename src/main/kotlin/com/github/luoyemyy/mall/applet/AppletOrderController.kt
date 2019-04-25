@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.service2.AppletOrderService
import com.github.luoyemyy.mall.core.service2.AppletPostageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-订单"])
@RestController
@RequestMapping("applet/order")
class AppletOrderController : BaseController() {

    @Autowired
    private lateinit var appletOrderService: AppletOrderService

    /**
     *
     */
    @ApiOperation("订单数量-待支付，待收货，待退款订单数量")
    @RequestApplet
    @GetMapping("index")
    fun index(): DataResponse<AppletOrderIndex> {
        return dataResponse(appletOrderService.index(userId()))
    }

    /**
     *
     */
    @ApiOperation("订单列表")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "type", value = "1 待支付 2 待收货 3 退款、退货 4 全部", required = true, dataTypeClass = Int::class),
        ApiImplicitParam(name = "page", value = "页码", required = true, dataTypeClass = Int::class)
    ])
    @GetMapping("list")
    fun list(type: Int, page: Int): ListResponse<AppletOrderItem> {
        return listResponse(appletOrderService.list(userId(), type, page))
    }

    /**
     *
     */
    @ApiOperation("订单详情")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @GetMapping("get")
    fun get(orderId: Long): DataResponse<AppletOrderInfo> {
        return dataResponse(appletOrderService.info(userId(), orderId))
    }


    /**
     *
     */
    @ApiOperation("支付完成")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)
    ])
    @GetMapping("pay/complete")
    fun payComplete(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.payComplete(userId(), orderId))
    }

    /**
     *
     */
    @ApiOperation("申请取消订单")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("cancel")
    fun cancel(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.cancel(userId(), orderId))
    }

    /**
     *
     */
    @ApiOperation("确认收货")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("accept")
    fun accept(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.accept(userId(), orderId))
    }

    /**
     *
     */
    @ApiOperation("申请退货")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("back")
    fun back(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.back(userId(), orderId))
    }
}