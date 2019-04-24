package com.github.luoyemyy.mall.core.wx.bean

class RefundResponse(xml: String) : BaseResponse(xml) {

    fun getWxRefundNo(): String? {
        return map?.get("refund_id")?.toString()
    }
}