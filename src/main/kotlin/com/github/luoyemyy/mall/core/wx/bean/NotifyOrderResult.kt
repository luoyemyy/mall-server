package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.util.wxSign

class NotifyOrderResult : BaseResult() {
    var sign_type: String? = null       //签名类型	    sign_type	    否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
    var device_info: String? = null     //设备号	        device_info	    否	String(32)	013467007045764	自定义参数，可以为请求支付的终端设备号等
    var openid: String? = null          //用户标识	    openid	        是	String(128)	wxd930ea5d5a258f4f	用户在商户appid下的唯一标识
    var is_subscribe: String? = null    //是否关注公众账号is_subscribe	是	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注
    var trade_type: String? = null      //交易类型	    trade_type	    是	String(16)	JSAPI	JSAPI、NATIVE、APP
    var bank_type: String? = null       //付款银行	    bank_type	    是	String(16)	CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
    var total_fee: Int = 0              //订单金额	    total_fee	    是	Int	100	    订单总金额，单位为分
    var settlement_total_fee: Int = 0 //应结订单金额 settlement_total_fee 否	Int	100	    应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    var fee_type: String? = null        //货币种类	    fee_type	    否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    var cash_fee: Int = 0               //现金支付金额	cash_fee	    是	Int	100	    现金支付金额订单现金支付金额，详见支付金额
    var cash_fee_type: String? = null   //现金支付货币类型cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    var transaction_id: String? = null  //微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
    var out_trade_no: String? = null    //商户订单号	    out_trade_no	是	String(32)	1212321211201407033568112322	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    var attach: String? = null          //商家数据包	    attach	        否	String(128)	123456	商家数据包，原样返回
    var time_end: String? = null        //支付完成时间	time_end	    是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则

    fun success(mchKey: String?): Boolean {
        return super.success() && wxSign(this, mchKey) == sign
    }
}

