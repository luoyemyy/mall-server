package com.github.luoyemyy.mall.controller.manager

import com.aliyun.oss.common.auth.HmacSHA1Signature
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.common.properties.AliOss
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.core.service.admin.UserService
import com.github.luoyemyy.mall.core.service.admin.bean.LoginUser
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Size

@Api(tags = ["后台-登录"])
@RestController
@RequestMapping("admin")
class ManagerLoginController03 : BaseController() {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var aliOss: AliOss

    @AppApi(pathId = 10301,auth = false)
    @ApiOperation("登录")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "phone", value = "手机号", paramType = "query", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "password", value = "密码，已用md5加密", paramType = "query", required = true, dataTypeClass = String::class)])

    @GetMapping("login")
    fun login(@Valid @Size(min = 6, message = "不少于6位") phone: String,
              @Valid @Size(min = 7, message = "不少于7位") password: String): DataResponse<LoginUser> {
        return dataResponse(userService.adminLogin(phone, password))
    }

    @AppApi(pathId = 10302,auth = false)
    @ApiOperation("阿里云文件上传token")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "content", value = "上传content", paramType = "body", required = true, dataTypeClass = String::class)])

    @GetMapping("aliossSign")
    fun alioss(content: String): DataResponse<String> {
        val signature = HmacSHA1Signature().computeSignature(aliOss.secretKey, content).trim()
        val sign = "OSS ${aliOss.accessKey}:$signature"
        return dataResponse(sign)
    }

}