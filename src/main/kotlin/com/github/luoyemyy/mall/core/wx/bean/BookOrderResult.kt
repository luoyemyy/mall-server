package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.util.wxSign

class BookOrderResult : BaseResult() {
    var device_info: String? = null //设备号	            device_info	否	String(32)	013467007045764	自定义参数，可以为请求支付的终端设备号等
    var trade_type: String? = null  //交易类型	        trade_type	是	String(16)	JSAPI	交易类型，取值为：JSAPI，NATIVE，APP等，说明详见参数规定
    var prepay_id: String? = null   //预支付交易会话标识	prepay_id	是	String(64)	wx201410272009395522657a690389285100	微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
    var code_url: String? = null    //二维码链接	        code_url	否	String(64)

    fun success(mchKey: String?): Boolean {
        return super.success() && wxSign(this, mchKey) == sign
    }
}

