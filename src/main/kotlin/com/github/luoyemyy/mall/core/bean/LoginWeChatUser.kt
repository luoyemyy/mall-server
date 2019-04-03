package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信登录用户")
class LoginWeChatUser {

    /**
     * user
     */
    @ApiModelProperty(value = "用户id")
    var id: Long = 0

    @ApiModelProperty(value = "头像url")
    var headImage: String? = null

    @ApiModelProperty(value = "用户名")
    var name: String? = null

    @ApiModelProperty(value = "手机号")
    var phone: String? = null

    @ApiModelProperty(value = "性别 0 未知 1 男 2女")
    var gender: Int = 0

    /**
     * 自定义
     */
    @ApiModelProperty(value = "登录token")
    var token: String? = null

    @ApiModelProperty(value = "微信身份open id")
    var openId: String? = null

    @ApiModelProperty(value = "微信身份union id")
    var unionId: String? = null
}