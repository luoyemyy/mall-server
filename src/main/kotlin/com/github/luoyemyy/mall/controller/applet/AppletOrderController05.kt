@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.*
import com.github.luoyemyy.mall.core.service.applet.AppletOrderService
import com.github.luoyemyy.mall.core.service.applet.bean.AppletOrderIndex
import com.github.luoyemyy.mall.core.service.applet.bean.AppletOrderInfo
import com.github.luoyemyy.mall.core.service.applet.bean.AppletOrderItem
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-订单"])
@RestController
@RequestMapping("applet/order")
class AppletOrderController05 : BaseController() {

    @Autowired
    private lateinit var appletOrderService: AppletOrderService

    @AppApi(pathId = 20501, auth = true)
    @ApiOperation("订单数量-待支付，待收货，待退款订单数量")
   
    @PostMapping("index")
    fun index(): DataResponse<AppletOrderIndex> {
        return dataResponse(appletOrderService.index(userId()))
    }

    @AppApi(pathId = 20502, auth = true)
    @ApiOperation("订单列表")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "type", value = "1 待支付 2 待收货 3 退款、退货 4 全部", required = true, dataTypeClass = Int::class),
        ApiImplicitParam(name = "page", value = "页码", required = true, dataTypeClass = Int::class)
    ])
    @PostMapping("list")
    fun list(type: Int, page: Int): ListResponse<AppletOrderItem> {
        return listResponse(appletOrderService.list(userId(), type, page))
    }

    @AppApi(pathId = 20503, auth = true)
    @ApiOperation("订单详情")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("get")
    fun get(orderId: Long): DataResponse<AppletOrderInfo> {
        return dataResponse(appletOrderService.info(userId(), orderId))
    }


    @AppApi(pathId = 20504, auth = true)
    @ApiOperation("支付完成")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)
    ])
    @PostMapping("pay/complete")
    fun payComplete(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.payComplete(userId(), orderId))
    }

    @AppApi(pathId = 20505, auth = true)
    @ApiOperation("申请取消订单")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("cancel")
    fun cancel(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.cancel(userId(), orderId))
    }

    @AppApi(pathId = 20506, auth = true)
    @ApiOperation("确认收货")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("accept")
    fun accept(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.accept(userId(), orderId))
    }

    @AppApi(pathId = 20507, auth = true)
    @ApiOperation("申请退货")
   
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("back")
    fun back(orderId: Long): ApiResponse {
        return apiResponse(appletOrderService.back(userId(), orderId))
    }
}