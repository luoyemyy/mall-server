@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.response.*
import com.github.luoyemyy.mall.core.applet.bean.AppletAddress
import com.github.luoyemyy.mall.core.applet.AppletAddressService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-收货地址"])
@RestController
@RequestMapping("applet/address")
class AppletAddressController01 : BaseController() {

    @Autowired
    private lateinit var appletAddressService: AppletAddressService

    @AppApi(pathId = 20101)
    @ApiOperation("收货地址列表")
    @PostMapping("list")
    fun list(): ListResponse<AppletAddress> {
        return listResponse(appletAddressService.list(userId()))
    }

    @AppApi(pathId = 20102)
    @ApiOperation("添加收货地址")
    @PostMapping("add")
    fun add(@RequestBody address: AppletAddress): ApiResponse {
        return apiResponse(appletAddressService.add(userId(), address))
    }

    @AppApi(pathId = 20103)
    @ApiOperation("添加收货地址")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "地址id", required = true, dataTypeClass = Long::class)])
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(appletAddressService.delete(userId(), id))
    }

    @AppApi(pathId = 20104)
    @ApiOperation("设为默认收货地址")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "地址id", required = true, dataTypeClass = Long::class)])
    @PostMapping("set/default")
    fun setDefault(id: Long): ApiResponse {
        return apiResponse(appletAddressService.setDefault(userId(), id))
    }

    @AppApi(pathId = 20105)
    @ApiOperation("获得默认收货地址")
    @PostMapping("get/default")
    fun getDefault(): DataResponse<AppletAddress> {
        return dataResponse(appletAddressService.getDefault(userId()))
    }
}