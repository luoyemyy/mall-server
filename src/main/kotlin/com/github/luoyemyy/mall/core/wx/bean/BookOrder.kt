package com.github.luoyemyy.mall.core.wx.bean

import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.util.newRandomString
import com.github.luoyemyy.mall.util.toXmlString
import com.github.luoyemyy.mall.util.wxSign

class BookOrder constructor() {

    var appid: String? = null               //小程序ID	    appid	    是	String(32)	wxd678efh567hg6787	微信分配的小程序ID
    var mch_id: String? = null              //商户号	        mch_id	    是	String(32)	1230000109	微信支付分配的商户号
    var device_info: String? = null         //设备号	        device_info	否	String(32)	013467007045764	自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
    var nonce_str: String? = null           //随机字符串	    nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，长度要求在32位以内。推荐随机数生成算法
    var sign: String? = null                //签名	        sign	    是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	通过签名算法计算得出的签名值，详见签名生成算法
    var sign_type: String? = null           //签名类型	    sign_type	否	String(32)	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。
    var body: String? = null                //商品描述	    body	    是	String(128)	腾讯充值中心-QQ会员充值 商品简单描述，该字段请按照规范传递，具体请见参数规定
    var detail: String? = null              //商品详情	    detail	    否	String(6000)商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
    var attach: String? = null              //附加数据	    attach	    否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
    var out_trade_no: String? = null        //商户订单号	   out_trade_no 是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
    var fee_type: String? = null            //标价币种	    fee_type	否	String(16)	CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
    var total_fee: Int = 0                  //标价金额	    total_fee	是	Int	88	    订单总金额，单位为分，详见支付金额
    var spbill_create_ip: String? = null    //终端IP	   spbill_create_ip 是	String(64)	123.12.12.123	支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
    var time_start: String? = null          //交易起始时间	time_start	否	String(14)	20091225091010	订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    var time_expire: String? = null         //交易结束时间	time_expire	否	String(14)	20091227091010
    var goods_tag: String? = null           //订单优惠标记	goods_tag	否	String(32)	WXG	订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
    var notify_url: String? = null          //通知地址	    notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
    var trade_type: String? = null          //交易类型	    trade_type	是	String(16)	JSAPI	小程序取值如下：JSAPI，详细说明见参数规定
    var product_id: String? = null          //商品ID	        product_id	否	String(32)	12235413214070356458058	trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
    var limit_pay: String? = null           //指定支付方式	limit_pay	否	String(32)	no_credit	上传此参数no_credit--可限制用户不能使用信用卡支付
    var openid: String? = null              //用户标识	    openid	    否	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。
    //var receipt: String? = null           //电子发票入口开放标识receipt	否	String(8)	Y	Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
    //var scene_info: String? = null        //+场景信息	    scene_info	否	String(256) 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开

    /**
     * @param appletInfo 商户信息
     * @param orderNo    订单编号
     * @param amount     金额
     * @param openId     微信用户
     */
    constructor(appletInfo: AppletInfo, orderNo: String, amount: Int, openId: String) : this() {
        this.appid = appletInfo.appId
        this.mch_id = appletInfo.mchId
        this.body = appletInfo.body
        this.spbill_create_ip = appletInfo.spbillCreateIp
        this.notify_url = appletInfo.notifyUrl
        this.trade_type = appletInfo.tradeType
        this.nonce_str = newRandomString(16)
        this.openid = openId
        this.out_trade_no = orderNo
        this.total_fee = amount
        this.sign = wxSign(this,appletInfo.mchKey)
    }

    fun buildXml(): String {
        return this.toXmlString()
    }
}

//<xml>
//<appid>wx2421b1c4370ec43b</appid>
//<attach>支付测试</attach>
//<body>JSAPI支付测试</body>
//<mch_id>10000100</mch_id>
//<detail><![CDATA[{ "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", "goods_name":"iPhone6s 16G", "quantity":1, "price":528800, "goods_category":"123456", "body":"苹果手机" }, { "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":"iPhone6s 32G", "quantity":1, "price":608800, "goods_category":"123789", "body":"苹果手机" } ] }]]></detail>
//<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
//<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>
//<openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
//<out_trade_no>1415659990</out_trade_no>
//<spbill_create_ip>14.23.150.211</spbill_create_ip>
//<total_fee>1</total_fee>
//<trade_type>JSAPI</trade_type>
//<sign>0CB01533B8C1EF103065174F50BCA001</sign>
//</xml>