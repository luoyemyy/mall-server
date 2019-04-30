package com.github.luoyemyy.mall.core.applet.bean

import com.github.luoyemyy.mall.core.entity.User
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信登录用户")
class AppletLoginUser {

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

    companion object {
        fun fromUser(user: User, token: String): AppletLoginUser {
            return AppletLoginUser().apply {
                id = user.id
                headImage = user.headImage
                name = user.name
                phone = user.phone
                gender = user.gender ?: 0
                this.token = token
            }
        }
    }
}