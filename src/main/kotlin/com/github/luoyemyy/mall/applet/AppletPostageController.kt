@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.*
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
class AppletPostageController : BaseController() {

    @Autowired
    private lateinit var appletPostageService: AppletPostageService

    /**
     *
     */
    @ApiOperation("获得匹配的邮费")
    @RequestApplet(needLogin = false)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "addressId", value = "地址id", required = true, dataTypeClass = Long::class)])
    @GetMapping("match")
    fun match(addressId: Long): DataResponse<AppletPostage> {
        return dataResponse(appletPostageService.match(addressId))
    }

    @ApiOperation("免邮总额")
    @RequestApplet(needLogin = false)
    @GetMapping("free")
    fun free(): DataResponse<Float> {
        return dataResponse(appletPostageService.free())
    }

}