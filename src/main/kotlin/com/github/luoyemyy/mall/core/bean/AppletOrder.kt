package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信邮费")
class AppletOrder {
    @ApiModelProperty("总额")
    var money: Float = 0f
    @ApiModelProperty("地址Id")
    var addressId: Long = 0
    @ApiModelProperty("邮费")
    var postage: Float = 0f
    @ApiModelProperty("产品")
    var products: List<AppletOrderProduct>? = null
}