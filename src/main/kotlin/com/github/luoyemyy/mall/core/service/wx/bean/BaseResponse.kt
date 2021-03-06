package com.github.luoyemyy.mall.core.service.wx.bean

import com.github.luoyemyy.mall.util.wxResponseSign
import com.github.luoyemyy.mall.util.xmlToMap

open class BaseResponse(xml: String) {

    val map = xml.xmlToMap<String, Any?>()

    fun success(mchKey: String?): Boolean {
        val returnCode = map?.get("return_code") ?: return false
        val resultCode = map["result_code"] ?: return false
        val sign = map["sign"] ?: return false
        return "SUCCESS" == returnCode && "SUCCESS" == resultCode && wxResponseSign(map, mchKey) == sign
    }
}