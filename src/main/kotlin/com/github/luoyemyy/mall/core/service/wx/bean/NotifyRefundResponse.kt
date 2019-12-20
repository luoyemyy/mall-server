package com.github.luoyemyy.mall.core.service.wx.bean

import com.github.luoyemyy.mall.util.aesDecode
import com.github.luoyemyy.mall.util.md5
import com.github.luoyemyy.mall.util.xmlToMap

class NotifyRefundResponse(xml: String) {

    private val map = xml.xmlToMap<String, Any?>()
    private var subMap: Map<String, Any?>? = null

    fun success(mchKey: String?): Boolean {
        val returnCode = map?.get("return_code") ?: return false
        if ("SUCCESS" == returnCode) {
            val key = mchKey?.md5()?.toLowerCase() ?: return false
            map["req_info"]?.toString()?.apply {
                subMap = aesDecode(key).xmlToMap() ?: return false
                if ("SUCCESS" == subMap?.get("refund_status")) {
                    return true
                }
            }
        }
        return false
    }

    fun getOrderNo(): String? {
        return subMap?.get("out_trade_no")?.toString()
    }

}