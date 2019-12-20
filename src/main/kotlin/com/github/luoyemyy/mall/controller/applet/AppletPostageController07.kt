@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.core.service.applet.AppletPostageService
import com.github.luoyemyy.mall.core.service.applet.bean.AppletPostage
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-邮费"])
@RestController
@RequestMapping("applet/postage")
class AppletPostageController07 : BaseController() {

    @Autowired
    private lateinit var appletPostageService: AppletPostageService

    @AppApi(pathId = 20701, auth = false)
    @ApiOperation("获得匹配的邮费")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "addressId", value = "地址id", required = true, dataTypeClass = Long::class)])
    @PostMapping("match")
    fun match(addressId: Long): DataResponse<AppletPostage> {
        return dataResponse(appletPostageService.match(addressId))
    }

    @AppApi(pathId = 20701, auth = false)
    @ApiOperation("免邮总额")
    @PostMapping("free")
    fun free(): DataResponse<Float> {
        return dataResponse(appletPostageService.free())
    }

}