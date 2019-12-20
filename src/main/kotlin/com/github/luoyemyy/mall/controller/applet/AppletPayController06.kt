@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.wx.WxPayService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-下单"])
@RestController
@RequestMapping("applet/pay")
class AppletPayController06 : BaseController() {

    @Autowired
    private lateinit var wxPayService: WxPayService

    @AppApi(pathId = 20601, auth = true)
    @ApiOperation("下单")
    @RequestApplet
    @PostMapping("book")
    fun book(@RequestBody order: AppletBookOrder): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookOrder(userId(), order))
    }

    @AppApi(pathId = 20602, auth = true)
    @ApiOperation("重新支付")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @GetMapping("book/pay/retry")
    fun bookPay(orderId: Long): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookPayRetry(userId(), orderId))
    }

    @AppApi(pathId = 20603, auth = true)
    @ApiOperation("查询订单支付结果")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @GetMapping("book/pay/query")
    fun bookQuery(orderId: Long): DataResponse<Boolean> {
        return dataResponse(wxPayService.queryOrder(userId(), orderId))
    }

    @AppApi(pathId = 20604, auth = true)
    @ApiOperation("下单结果通知")
    @RequestApplet(needLogin = false)
    @PostMapping("book/notify")
    fun bookNotify(@RequestBody xml: String): String {
        return wxPayService.bookOrderNotify(xml)
    }

    @AppApi(pathId = 20605, auth = true)
    @ApiOperation("退款结果通知")
    @RequestApplet(needLogin = false)
    @PostMapping("refund/notify")
    fun refundNotify(@RequestBody xml: String): String {
        return wxPayService.refundNotify(xml)
    }
}
