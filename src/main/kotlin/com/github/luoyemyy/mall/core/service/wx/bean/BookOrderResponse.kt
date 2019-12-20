package com.github.luoyemyy.mall.core.service.wx.bean

class BookOrderResponse(xml: String) : BaseResponse(xml) {

    fun getPayId(): String? {
        return map?.get("prepay_id")?.toString()
    }
}