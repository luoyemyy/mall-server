package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel("订单")
open class OrderItem {

    @ApiModelProperty("订单id", required = true)
    var orderId: Long = 0
    @ApiModelProperty("订单状态", required = true)
    var state:Int=0
    @ApiModelProperty("收件人", required = true)
    var username: String? = null
    @ApiModelProperty("电话", required = true)
    var phone: String? = null
    @ApiModelProperty("地址", required = true)
    var address: String? = null
    @ApiModelProperty("金额", required = true)
    var money: Float = 0f
    @ApiModelProperty("数量", required = true)
    var count: Int = 0
    @ApiModelProperty("时间", required = true)
    var date: Date? = null

}