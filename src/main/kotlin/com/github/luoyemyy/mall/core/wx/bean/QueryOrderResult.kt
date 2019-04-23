package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.util.wxSign

class QueryOrderResult : BaseResult() {

    var device_info: String? = null         //设备号	        device_info	    否	String(32)	013467007045764	微信支付分配的终端设备号，
    var openid: String? = null              //用户标识	    openid	        是	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	用户在商户appid下的唯一标识
    var is_subscribe: String? = null        //是否关注公众账号is_subscribe    是	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注
    var trade_type: String? = null          //交易类型	    trade_type	    是	String(16)	JSAPI	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
    var trade_state: String? = null         //交易状态	    trade_state	    是	String(32)	SUCCESS SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
    var bank_type: String? = null           //付款银行	    bank_type	    是	String(16)	CMC	银行类型，采用字符串类型的银行标识
    var total_fee: Int = 0                  //标价金额	    total_fee	    是	Int	100	    订单总金额，单位为分
    var fee_type: String? = null            //标价币种	    fee_type	    否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    var cash_fee: Int = 0                   //现金支付金额	cash_fee	    是	Int	100	    现金支付金额订单现金支付金额，详见支付金额
    var cash_fee_type: String? = null       //现金支付币种	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    var transaction_id: String? = null      //微信支付订单号	transaction_id	是	String(32)	1009660380201506130728806387	微信支付订单号
    var out_trade_no: String? = null        //商户订单号	    out_trade_no	是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    var attach: String? = null              //附加数据	    attach	        否	String(128)	深圳分店	附加数据，原样返回
    var time_end: String? = null            //支付完成时间	time_end	    是	String(14)	20141030133525	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    var trade_state_desc: String? = null    //交易状态描述	trade_state_desc是	String(256)	支付失败，请重新下单支付	对当前查询订单状态的描述和下一步操作的指引

    fun success(mchKey: String?): Boolean {
        return super.success() && "SUCCESS" == trade_state && wxSign(this, mchKey) == sign
    }
}