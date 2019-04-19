package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信下单产品")
class AppletBookOrderProduct {
    @ApiModelProperty("产品id")
    var productId: Long = 0
    @ApiModelProperty("产品单价")
    var price: Float = 0f
    @ApiModelProperty("产品数量")
    var count: Int = 0
}