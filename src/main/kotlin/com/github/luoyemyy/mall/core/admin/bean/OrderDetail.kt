package com.github.luoyemyy.mall.core.admin.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel("订单详情")
open class OrderDetail {

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
    @ApiModelProperty("商品总数")
    var productCount: Int = 0
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
    @ApiModelProperty("支付时间")
    var payTime: Date? = null
    @ApiModelProperty("运送时间")
    var deliverTime: Date? = null
    @ApiModelProperty("签收时间")
    var signTime: Date? = null
    @ApiModelProperty("微信支付id")
    var wxPayId: String? = null
    @ApiModelProperty("微信支付编号")
    var wxOrderId: String? = null
    @ApiModelProperty("快递公司名称")
    var expressCompany: String? = null
    @ApiModelProperty("快递单号")
    var expressNo: String? = null
    @ApiModelProperty("取消原因")
    var cancelReason: String? = null
    @ApiModelProperty("退款单号")
    var refuseOrderNo: String? = null
    @ApiModelProperty("退款微信单号")
    var refuseWxNo: String? = null

    @ApiModelProperty("产品")
    var products: List<OrderProduct>? = null

}