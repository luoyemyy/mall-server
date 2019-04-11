@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.apiResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.bean.AppletAddress
import com.github.luoyemyy.mall.core.bean.AppletCart
import com.github.luoyemyy.mall.core.bean.AppletHotBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.service2.AppletAddressService
import com.github.luoyemyy.mall.core.service2.AppletCartService
import com.github.luoyemyy.mall.core.service2.AppletHotService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-收货地址"])
@RestController
@RequestMapping("applet/address")
class AppletAddressController : BaseController() {

    @Autowired
    private lateinit var appletAddressService: AppletAddressService

    /**
     *
     */
    @ApiOperation("收货地址列表")
    @RequestApplet
    @GetMapping("list")
    fun list(): ListResponse<AppletAddress> {
        return listResponse(appletAddressService.list(userId()))
    }


    /**
     *
     */
    @ApiOperation("添加收货地址")
    @RequestApplet
    @PostMapping("add")
    fun add(@RequestBody address: AppletAddress): ApiResponse {
        return apiResponse(appletAddressService.add(userId(), address))
    }

    /**
     *
     */
    @ApiOperation("添加收货地址")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "地址id", required = true, dataTypeClass = Long::class)])
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(appletAddressService.delete(userId(), id))
    }

    /**
     *
     */
    @ApiOperation("设为默认收货地址")
    @RequestApplet
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "地址id", required = true, dataTypeClass = Long::class)])
    @PostMapping("type/default")
    fun setDefault(id: Long): ApiResponse {
        return apiResponse(appletAddressService.setDefault(userId(), id))
    }
}