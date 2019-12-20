package com.github.luoyemyy.mall.core.service.applet.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信订单列表")
class AppletOrderItem {
    @ApiModelProperty("订单id")
    var id: Long = 0
    @ApiModelProperty("状态")
    var state: Int = 0
    @ApiModelProperty("金额")
    var money: Float = 0f

    @ApiModelProperty("产品")
    var products: List<AppletOrderProduct>? = null
}