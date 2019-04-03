package com.github.luoyemyy.mall.core.bean

import com.github.luoyemyy.mall.core.entity.User
import com.github.luoyemyy.mall.util.Role
import com.github.luoyemyy.mall.util.RoleInfo
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("登录用户")
class LoginUser {

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

    @ApiModelProperty(value = "角色Id")
    var roleId: Int = 0

    @ApiModelProperty(value = "角色名称")
    var roleName: String? = null

    @ApiModelProperty("当前用户可使用的角色列表")
    var roles: List<RoleInfo>? = null

    @ApiModelProperty("云存储accessKey")
    var ossAk: String? = null

    @ApiModelProperty("云存储secretKey")
    var ossSk: String? = null

    @ApiModelProperty("云存储endPoint")
    var ossEp: String? = null

    @ApiModelProperty("云存储bucket")
    var ossBucket: String? = null

    companion object {
        fun fromUser(user: User, token: String, roleId: Int): LoginUser {
            return LoginUser().apply {
                id = user.id
                headImage = user.headImage
                name = user.name
                phone = user.phone
                gender = user.gender
                this.token = token
                this.roleId = roleId
                roleName = Role.getName(roleId)
                roles = Role.getRoles(roleId)
            }
        }
    }
}