package com.github.luoyemyy.mall.core.wx

import org.springframework.stereotype.Service

@Service
class WxPayService{

    companion object {
        const val URL_BOOK_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder"                 //下单
        const val URL_QUERY_ORDER = "https://api.mch.weixin.qq.com/pay/orderquery"                  //查订单
        const val URL_CLOSE_ORDER = "https://api.mch.weixin.qq.com/pay/closeorder"                  //关闭订单
        const val URL_REFUND_ORDER = "https://api.mch.weixin.qq.com/secapi/pay/refund"              //查退款
        const val URL_QUERY_REFUND_ORDER = "https://api.mch.weixin.qq.com/pay/refundquery"          //查退款
        const val URL_SANBOX_BOOK_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder"
        const val URL_SANBOX_QUERY_ORDER = "https://api.mch.weixin.qq.com/pay/orderquery"
        const val URL_SANBOX_CLOSE_ORDER = "https://api.mch.weixin.qq.com/pay/closeorder"
        const val URL_SANBOX_REFUND_ORDER = "https://api.mch.weixin.qq.com/secapi/pay/refund"
        const val URL_SANBOX_QUERY_REFUND_ORDER = "https://api.mch.weixin.qq.com/pay/refundquery"
    }

    fun bookOrder(){

    }
}