@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.core.service.applet.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.service.applet.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.service.wx.WxPayService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-下单"])
@RestController
@RequestMapping("applet/pay")
class AppletPayController06 : BaseController() {

    @Autowired
    private lateinit var wxPayService: WxPayService

    @AppApi(pathId = 20601, auth = true)
    @ApiOperation("下单")
    @PostMapping("book")
    fun book(@RequestBody order: AppletBookOrder): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookOrder(userId(), order))
    }

    @AppApi(pathId = 20602, auth = true)
    @ApiOperation("重新支付")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("book/pay/retry")
    fun bookPay(orderId: Long): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookPayRetry(userId(), orderId))
    }

    @AppApi(pathId = 20603, auth = true)
    @ApiOperation("查询订单支付结果")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @PostMapping("book/pay/query")
    fun bookQuery(orderId: Long): DataResponse<Boolean> {
        return dataResponse(wxPayService.queryOrder(userId(), orderId))
    }

    @AppApi(pathId = 20604, auth = false)
    @ApiOperation("下单结果通知")
    @PostMapping("book/notify")
    fun bookNotify(@RequestBody xml: String): String {
        return wxPayService.bookOrderNotify(xml)
    }

    @AppApi(pathId = 20605, auth = false)
    @ApiOperation("退款结果通知")
    @PostMapping("refund/notify")
    fun refundNotify(@RequestBody xml: String): String {
        return wxPayService.refundNotify(xml)
    }
}
