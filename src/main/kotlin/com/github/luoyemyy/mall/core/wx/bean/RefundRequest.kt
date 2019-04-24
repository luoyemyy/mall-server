package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.toXmlString
import com.github.luoyemyy.mall.util.wxSign2

class RefundRequest constructor(appletInfo: AppletInfo, wxOrderNo: String, refundOrderNo: String, money: Int, refundMoney: Int) {

    val map = mutableMapOf<String, Any?>()

    init {
        map["appid"] = appletInfo.appId
        map["mch_id"] = appletInfo.mchId
        map["nonce_str"] = newRandomString(16)
        map["transaction_id"] = wxOrderNo
        map["out_refund_no"] = refundOrderNo
        map["total_fee"] = money
        map["refund_fee"] = refundMoney
        map["notify_url"] = appletInfo.refundNotifyUrl
        map["sign"] = wxSign2(map, appletInfo.mchKey)
    }

    fun toXml(): String {
        return map.toXmlString()
    }
}