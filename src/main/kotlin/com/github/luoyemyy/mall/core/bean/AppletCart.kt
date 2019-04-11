package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("购物车产品")
open class AppletCart {

    @ApiModelProperty("产品id", required = true)
    var id: Long = 0
    @ApiModelProperty("产品封面", required = true)
    var coverImage: String? = null
    @ApiModelProperty("产品名称", required = true)
    var name: String? = null
    @ApiModelProperty("市场价格", required = true)
    var marketPrice: Float = 0f
    @ApiModelProperty("实际价格", required = true)
    var actualPrice: Float = 0f
    @ApiModelProperty("数量", required = true)
    var count: Int = 0

}