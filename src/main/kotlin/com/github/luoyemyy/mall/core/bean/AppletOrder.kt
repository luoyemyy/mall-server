package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信下单")
class AppletOrder {
    @ApiModelProperty("总额")
    var money: Float = 0f
    @ApiModelProperty("地址")
    var address: AppletAddress? = null
    @ApiModelProperty("邮费")
    var postage: AppletOrderPostage? = null
    @ApiModelProperty("产品")
    var products: List<AppletOrderProduct>? = null
}