package com.github.luoyemyy.mall.core.wx.bean

open class BaseResult {

    var return_code: String? = null     //返回状态码	    return_code	是	String(16)	SUCCESS SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    var return_msg: String? = null      //返回信息	    return_msg	否	String(128)	签名失败 返回信息，如非空，为错误原因，签名失败，参数格式校验错误
    var appid: String? = null           //小程序ID	    appid	    是	String(32)	wx8888888888888888	调用接口提交的小程序ID
    var mch_id: String? = null          //商户号	        mch_id	    是	String(32)	1900000109	调用接口提交的商户号
    var nonce_str: String? = null       //随机字符串	    nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	微信返回的随机字符串
    var sign: String? = null            //签名	        sign	    是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	微信返回的签名值，详见签名算法
    var result_code: String? = null     //业务结果	    result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
    var err_code: String? = null        //错误代码	    err_code	否	String(32)	SYSTEMERROR	详细参见下文错误列表
    var err_code_des: String? = null    //错误代码描述	err_code_des否	String(128)	系统错误	错误信息描述

    protected fun success(): Boolean {
        return "SUCCESS" == return_code && "SUCCESS" == result_code
    }
}

