@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.DataResponse
import com.github.luoyemyy.mall.base.response.dataResponse
import com.github.luoyemyy.mall.core.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.wx.WxPayService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    @ApiOperation("下单结果通知")
    @RequestApplet(needLogin = false)
    @PostMapping("book/notify")
    fun bookNotify(@RequestBody xml: String): String {
        return wxPayService.bookOrderNotify(xml)
    }
}
