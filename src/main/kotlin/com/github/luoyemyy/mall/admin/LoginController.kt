package com.github.luoyemyy.mall.admin

import com.aliyun.oss.common.auth.HmacSHA1Signature
import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.config.AliOss
import com.github.luoyemyy.mall.base.response.DataResponse
import com.github.luoyemyy.mall.base.response.dataResponse
import com.github.luoyemyy.mall.core.admin.bean.LoginUser
import com.github.luoyemyy.mall.core.admin.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["后台-登录"])
@RestController
@RequestMapping("admin")
class LoginController : BaseController() {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var aliOss: AliOss

    @ApiOperation("登录")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "phone", value = "手机号", paramType = "body", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "password", value = "密码，已用md5加密", paramType = "body", required = true, dataTypeClass = String::class)])
    @RequestAdmin(false)
    @GetMapping("login")
    fun login(phone: String, password: String): DataResponse<LoginUser> {
        return dataResponse(userService.adminLogin(phone, password))
    }

    @ApiOperation("阿里云文件上传token")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "content", value = "上传content", paramType = "body", required = true, dataTypeClass = String::class)])
    @RequestAdmin(false)
    @GetMapping("aliossSign")
    fun alioss(content: String): DataResponse<String> {
        val signature = HmacSHA1Signature().computeSignature(aliOss.secretKey, content).trim()
        val sign = "OSS ${aliOss.accessKey}:$signature"
        return dataResponse(sign)
    }

}