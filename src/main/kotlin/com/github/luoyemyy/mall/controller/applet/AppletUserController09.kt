package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.core.applet.AppletUserService
import com.github.luoyemyy.mall.core.applet.bean.AppletLoginUser
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-用户"])
@RestController
@RequestMapping("applet/user")
class AppletUserController09 {

    @Autowired
    private lateinit var appletUserService: AppletUserService

    @AppApi(pathId = 20901, auth = false)
    @ApiOperation("登录")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "code", value = "授权码", paramType = "body", required = true, dataTypeClass = String::class)])
    @GetMapping("login")
    fun login(code: String): DataResponse<AppletLoginUser> {
        return dataResponse(appletUserService.login(code))
    }

}