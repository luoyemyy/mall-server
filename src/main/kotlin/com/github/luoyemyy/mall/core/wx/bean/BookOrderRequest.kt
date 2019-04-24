package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.toXmlString
import com.github.luoyemyy.mall.util.wxSign2

class BookOrderRequest(appletInfo: AppletInfo, orderNo: String, amount: Int, openId: String) {

    val map = mutableMapOf<String, Any?>()

    init {
        map["appid"] = appletInfo.appId
        map["mch_id"] = appletInfo.mchId
        map["nonce_str"] = newRandomString(16)
        map["body"] = appletInfo.body
        map["out_trade_no"] = orderNo
        map["total_fee"] = amount
        map["spbill_create_ip"] = appletInfo.spbillCreateIp
        map["notify_url"] = appletInfo.payNotifyUrl
        map["trade_type"] = appletInfo.tradeType
        map["openid"] = openId
        map["sign"] = wxSign2(map, appletInfo.mchKey)
    }

    fun toXml(): String {
        return map.toXmlString()
    }
}