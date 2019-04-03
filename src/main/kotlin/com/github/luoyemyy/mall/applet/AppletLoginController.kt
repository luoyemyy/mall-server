package com.github.luoyemyy.mall.applet

import com.aliyun.oss.common.auth.HmacSHA1Signature
import com.aliyun.oss.internal.OSSRequestSigner
import com.aliyun.oss.internal.OSSUtils
import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.config.AliOss
import com.github.luoyemyy.mall.base.response.DataResponse
import com.github.luoyemyy.mall.base.response.dataResponse
import com.github.luoyemyy.mall.core.bean.LoginUser
import com.github.luoyemyy.mall.core.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-登录"])
@RestController
@RequestMapping("applet")
class AppletLoginController : BaseController() {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var aliOss: AliOss

    @ApiOperation("登录")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "code", value = "授权码", paramType = "body", required = true, dataTypeClass = String::class)])
    @RequestApplet(false)
    @GetMapping("login")
    fun login(phone: String, password: String): DataResponse<LoginUser> {
        return dataResponse(userService.adminLogin(phone, password))
    }

    @ApiOperation("阿里云文件上传token")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "content", value = "上传content", paramType = "body", required = true, dataTypeClass = String::class)])
    @RequestApplet(false)
    @GetMapping("aliossSign")
    fun alioss(content: String): DataResponse<String> {
        val signature = HmacSHA1Signature().computeSignature(aliOss.secretKey, content).trim()
        val sign = "OSS ${aliOss.accessKey}:$signature"
        return dataResponse(sign)
    }

}