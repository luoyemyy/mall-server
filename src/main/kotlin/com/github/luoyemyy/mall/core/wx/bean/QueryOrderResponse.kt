package com.github.luoyemyy.mall.core.wx.bean

class QueryOrderResponse(xml: String) : BaseResponse(xml) {

    fun getWxOrderId(): String? {
        return map?.get("transaction_id")?.toString()
    }
}