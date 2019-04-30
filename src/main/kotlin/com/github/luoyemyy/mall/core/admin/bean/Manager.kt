package com.github.luoyemyy.mall.core.admin.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("用户")
open class Manager {

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
    @ApiModelProperty(value = "角色Id")
    var roleId: Int = 0

    @ApiModelProperty(value = "角色名称")
    var roleName: String? = null

    @ApiModelProperty(value = "是否有效 0 无效 1 有效")
    var state:Int = 0
}