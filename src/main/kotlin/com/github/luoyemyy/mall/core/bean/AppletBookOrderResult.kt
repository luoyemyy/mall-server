package com.github.luoyemyy.mall.core.bean

import com.github.luoyemyy.mall.util.md5
import com.github.luoyemyy.mall.util.newRandomString
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信下单结果")
class AppletBookOrderResult {
    @ApiModelProperty("订单id")
    var orderId: Long = 0
    @ApiModelProperty("统一下单id")
    var payId: String? = null
    @ApiModelProperty("模拟微信支付")
    var mock: Boolean = false

    //
    @ApiModelProperty("微信支付参数-时间戳")
    var timeStamp: Long = 0
    @ApiModelProperty("微信支付参数-随机字符串")
    var nonceStr: String? = null
    @ApiModelProperty("微信支付参数-统一下单id")
    var packages: String? = null
    @ApiModelProperty("微信支付参数-签名方式")
    var signType: String = "MD5"
    @ApiModelProperty("微信支付参数-签名")
    var paySign: String? = null

    fun buildParams() {
        timeStamp = System.currentTimeMillis()
        nonceStr = newRandomString(16)
        packages = "prepay_id=$payId"
        paySign = listOf("nonceStr=$nonceStr", "package=$packages", "signType=$signType", "timeStamp=$timeStamp")
                .joinToString("&").md5()?.toUpperCase()
    }
}