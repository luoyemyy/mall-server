@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.response.*
import com.github.luoyemyy.mall.core.applet.AppletPostageService
import com.github.luoyemyy.mall.core.applet.bean.AppletPostage
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    @GetMapping("match")
    fun match(addressId: Long): DataResponse<AppletPostage> {
        return dataResponse(appletPostageService.match(addressId))
    }

    @AppApi(pathId = 20701, auth = false)
    @ApiOperation("免邮总额")
    @GetMapping("free")
    fun free(): DataResponse<Float> {
        return dataResponse(appletPostageService.free())
    }

}