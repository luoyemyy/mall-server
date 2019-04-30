@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.apiResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.applet.bean.AppletCart
import com.github.luoyemyy.mall.core.applet.AppletCartService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-购物车"])
@RestController
@RequestMapping("applet/cart")
class AppletCartController : BaseController() {

    @Autowired
    private lateinit var appletCartService: AppletCartService

    /**
     *
     */
    @ApiOperation("购物车列表")
    @RequestApplet
    @GetMapping("list")
    fun list(): ListResponse<AppletCart> {
        return listResponse(appletCartService.list(userId()))
    }


    /**
     *
     */
    @ApiOperation("添加到购物车")
    @RequestApplet
    @PostMapping("save")
    fun save(@RequestBody carts: List<AppletCart>): ApiResponse {
        return apiResponse(appletCartService.save(userId(), carts))
    }

}