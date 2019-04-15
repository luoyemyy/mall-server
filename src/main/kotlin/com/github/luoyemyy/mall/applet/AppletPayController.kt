@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.entity.Product
import com.github.luoyemyy.mall.core.service.ProductService
import com.github.luoyemyy.mall.core.service2.AppletProductService
import com.github.luoyemyy.mall.core.wx.WxPayService
import com.github.luoyemyy.mall.util.Role
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
    fun book(@RequestBody order: AppletOrder): DataResponse<AppletOrderResult> {
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
