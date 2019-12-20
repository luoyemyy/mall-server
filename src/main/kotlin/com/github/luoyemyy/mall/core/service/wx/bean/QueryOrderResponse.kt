package com.github.luoyemyy.mall.core.service.wx.bean

class QueryOrderResponse(xml: String) : BaseResponse(xml) {

    fun getWxOrderId(): String? {
        return map?.get("transaction_id")?.toString()
    }
}