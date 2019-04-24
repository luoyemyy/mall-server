package com.github.luoyemyy.mall.core.wx.bean

class NotifyOrderResponse(xml: String) : BaseResponse(xml) {

    fun getOrderNo(): String? {
        return map?.get("out_trade_no")?.toString()
    }

    fun getWxOrderId(): String? {
        return map?.get("transaction_id")?.toString()
    }
}