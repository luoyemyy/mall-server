package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.wxSign

class QueryOrder constructor() {
    var appid: String? = null               //小程序ID	    appid	    是	String(32)	wxd678efh567hg6787	微信分配的小程序ID
    var mch_id: String? = null              //商户号	        mch_id	    是	String(32)	1230000109	微信支付分配的商户号
    var out_trade_no: String? = null        //商户订单号	   out_trade_no 是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
    var nonce_str: String? = null           //随机字符串	    nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，长度要求在32位以内。推荐随机数生成算法
    var sign: String? = null                //签名	        sign	    是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	通过签名算法计算得出的签名值，详见签名生成算法
    var sign_type: String? = null           //签名类型	    sign_type	否	String(32)	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。

    constructor(appletInfo: AppletInfo, orderNo: String) : this() {
        this.appid = appletInfo.appId
        this.mch_id = appletInfo.mchId
        this.out_trade_no = orderNo
        this.nonce_str = newRandomString(16)
        this.sign = wxSign(this, appletInfo.mchKey)
    }
}