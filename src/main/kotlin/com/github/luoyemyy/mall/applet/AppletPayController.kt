@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.DataResponse
import com.github.luoyemyy.mall.base.response.dataResponse
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.wx.WxPayService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-下单"])
@RestController
@RequestMapping("applet/pay")
class AppletPayController : BaseController() {

    @Autowired
    private lateinit var wxPayService: WxPayService

    /**
     *
     */
    @ApiOperation("下单")
    @RequestApplet
    @PostMapping("book")
    fun book(@RequestBody order: AppletBookOrder): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookOrder(userId(), order))
    }

    /**
     *
     */
    @ApiOperation("重新支付")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @GetMapping("book/pay/retry")
    fun bookPay(orderId: Long): DataResponse<AppletBookOrderResult> {
        return dataResponse(wxPayService.bookPayRetry(userId(), orderId))
    }

    /**
     *
     */
    @ApiOperation("查询订单支付结果")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)])
    @GetMapping("book/pay/query")
    fun bookQuery(orderId: Long): DataResponse<Boolean> {
        return dataResponse(wxPayService.queryOrder(userId(), orderId))
    }

    /**
     *
     */
    @ApiOperation("下单结果通知")
    @RequestApplet(needLogin = false)
    @PostMapping("book/notify")
    fun bookNotify(@RequestBody xml: String): String {
        return wxPayService.bookOrderNotify(xml)
    }

    /**
     *
     */
    @ApiOperation("退款结果通知")
    @RequestApplet(needLogin = false)
    @PostMapping("refund/notify")
    fun refundNotify(@RequestBody xml: String): String {
        return wxPayService.refundNotify(xml)
    }
}
