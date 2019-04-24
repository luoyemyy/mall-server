package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("订单产品")
open class OrderProduct {

    @ApiModelProperty("产品id", required = true)
    var id: Long = 0
    @ApiModelProperty("产品封面", required = true)
    var coverImage: String? = null
    @ApiModelProperty("产品名称", required = true)
    var name: String? = null
    @ApiModelProperty("价格", required = true)
    var price: Float = 0f
    @ApiModelProperty("数量", required = true)
    var count: Int = 0

}