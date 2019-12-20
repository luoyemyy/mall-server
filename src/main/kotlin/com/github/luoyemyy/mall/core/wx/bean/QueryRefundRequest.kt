package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.common.properties.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.toXmlString
import com.github.luoyemyy.mall.util.wxRequestSign

class QueryRefundRequest constructor(appletInfo: AppletInfo, wxOrderNo: String) {

    val map = mutableMapOf<String, Any?>()

    init {
        map["appid"] = appletInfo.appId
        map["mch_id"] = appletInfo.mchId
        map["nonce_str"] = newRandomString(16)
        map["transaction_id"] = wxOrderNo
        map["sign"] = wxRequestSign(map, appletInfo.mchKey)
    }

    fun toXml(): String {
        return map.toXmlString()
    }
}