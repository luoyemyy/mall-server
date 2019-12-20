package com.github.luoyemyy.mall.core.service.wx.bean

import com.github.luoyemyy.mall.common.properties.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.toXmlString
import com.github.luoyemyy.mall.util.wxRequestSign

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
        map["sign"] = wxRequestSign(map, appletInfo.mchKey)
    }

    fun toXml(): String {
        return map.toXmlString()
    }
}