@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.applet.AppletCartService
import com.github.luoyemyy.mall.core.service.applet.bean.AppletCart
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-购物车"])
@RestController
@RequestMapping("applet/cart")
class AppletCartController02 : BaseController() {

    @Autowired
    private lateinit var appletCartService: AppletCartService

    @ApiOperation("购物车列表")
    @AppApi(pathId = 20201)
    @PostMapping("list")
    fun list(): ListResponse<AppletCart> {
        return listResponse(appletCartService.list(userId()))
    }


    @AppApi(pathId = 20202)
    @ApiOperation("添加到购物车")
    @PostMapping("save")
    fun save(@RequestBody carts: List<AppletCart>): ApiResponse {
        return apiResponse(appletCartService.save(userId(), carts))
    }

}