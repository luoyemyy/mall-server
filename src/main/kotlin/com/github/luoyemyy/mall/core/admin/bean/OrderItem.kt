package com.github.luoyemyy.mall.core.admin.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel("订单")
open class OrderItem {

    @ApiModelProperty("订单id")
    var orderId: Long = 0
    @ApiModelProperty("订单状态")
    var state: Int = 0
    @ApiModelProperty("收件人")
    var username: String? = null
    @ApiModelProperty("电话")
    var phone: String? = null
    @ApiModelProperty("地址")
    var address: String? = null
    @ApiModelProperty("金额")
    var money: Float = 0f
    @ApiModelProperty("数量")
    var count: Int = 0
    @ApiModelProperty("创建时间")
    var createTime: Date? = null
    @ApiModelProperty("支付时间")
    var payTime: Date? = null
    @ApiModelProperty("运送时间")
    var deliverTime: Date? = null
    @ApiModelProperty("签收时间")
    var signTime: Date? = null

}