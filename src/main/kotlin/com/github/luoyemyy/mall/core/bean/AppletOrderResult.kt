package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信下单结果")
class AppletOrderResult {
    @ApiModelProperty("订单id")
    var orderId: Long = 0
    @ApiModelProperty("统一下单id")
    var payId: String? = null
}