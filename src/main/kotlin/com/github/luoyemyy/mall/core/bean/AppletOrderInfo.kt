package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel("微信订单详情")
class AppletOrderInfo {
    @ApiModelProperty("订单id")
    var id: Long = 0
    @ApiModelProperty("订单编号")
    var orderNo: String? = null
    @ApiModelProperty("用户id")
    var userId: Long = 0
    @ApiModelProperty("状态")
    var state: Int = 0
    @ApiModelProperty("金额")
    var money: Float = 0f
    @ApiModelProperty("邮费")
    var postage: Float = 0f
    @ApiModelProperty("收件人名称")
    var username: String? = null
    @ApiModelProperty("收件人电话")
    var phone: String? = null
    @ApiModelProperty("收件人地址")
    var address: String? = null
    @ApiModelProperty("收件人邮编")
    var postcode: String? = null
    @ApiModelProperty("创建时间")
    var createTime: Date? = null
    @ApiModelProperty("更新时间")
    var updateTime: Date? = null
    @ApiModelProperty("微信支付id")
    var wxPayId: String? = null
    @ApiModelProperty("微信支付编号")
    var wxOrderId: String? = null
    @ApiModelProperty("支付截止时间")
    var payLimitTime: Date? = null

    @ApiModelProperty("产品")
    var products: List<AppletOrderProduct>? = null

}