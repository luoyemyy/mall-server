package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.core.bean.AppletOrder
import com.github.luoyemyy.mall.core.bean.AppletOrderResult
import com.github.luoyemyy.mall.core.dao.KeyValueDao
import com.github.luoyemyy.mall.core.dao.WeChatDao
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.core.mapper.PostageMapper
import com.github.luoyemyy.mall.core.service.HttpService
import com.github.luoyemyy.mall.core.wx.bean.BookOrder
import com.github.luoyemyy.mall.core.wx.bean.BookOrderResult
import com.github.luoyemyy.mall.util.AppKey
import com.github.luoyemyy.mall.util.newOrderNo
import com.github.luoyemyy.mall.util.xmlToObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class WxPayService {

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

    @Autowired
    private lateinit var appletInfo: AppletInfo
    @Autowired
    private lateinit var httpService: HttpService
    @Autowired
    private lateinit var weChatDao: WeChatDao
    @Autowired
    private lateinit var postageMapper: PostageMapper
    @Autowired
    private lateinit var keyValueDao: KeyValueDao
    @Autowired
    private lateinit var orderMapper: OrderMapper

    private fun getFreePostage(): Float {
        return keyValueDao.selectByKey(AppKey.FREE_POSTAGE)?.valueLong?.toFloat() ?: 0f
    }

    /**
     * 下单
     */
    fun bookOrder(userId: Long, appletOrder: AppletOrder): AppletOrderResult {
        //检验用户
        val weChat = weChatDao.selectByUser(userId) ?: throw MallException(Code.BOOK_ORDER_USER_ERROR)
        val openId = weChat.openId ?: throw MallException(Code.BOOK_ORDER_USER_ERROR)
        //检验邮费
        val postage = appletOrder.postage ?: throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        val localPostage = postageMapper.selectByPrimaryKey(postage.id)
        if (localPostage == null || localPostage.post == 0) throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        val freePostage = getFreePostage()
        //不免邮费或订单金额小于免邮金额
        if (appletOrder.money < freePostage) {
            if (postage.price != localPostage.price) throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        }
        //检验金额
        val products = appletOrder.products ?: throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        var money = postage.price
        products.forEach {
            money += it.price * it.count
        }
        if (appletOrder.money != money) {
            throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        }
        //生成订单
        val address = appletOrder.address ?: throw MallException(Code.BOOK_ORDER_ADDRESS_ERROR)
        if (address.name.isNullOrEmpty() || address.phone.isNullOrEmpty() || address.summary.isNullOrEmpty() || address.postCode.isNullOrEmpty()) {
            throw MallException(Code.BOOK_ORDER_ADDRESS_ERROR)
        }
        val order = Order().apply {
            this.orderNo = newOrderNo()
            this.userId = userId
            this.state = 0
            this.money = money
            this.postage = postage.price
            this.username = address.name
            this.phone = address.phone
            this.address = address.summary
            this.postcode = address.postCode
            this.createTime = Date()
            this.updateTime = Date()
            this.wxPayId = null
            this.wxOrderId = null
            this.status = 1
        }
        val bookOrder = BookOrder(appletInfo, order.orderNo, (money * 100).toInt(), openId)
        //注册订单
        httpService.postXml(URL_BOOK_ORDER, bookOrder.buildXml())?.xmlToObject<BookOrderResult>()?.apply {
            if (success(appletInfo.mchKey)) {
                order.wxPayId = prepay_id
                if (orderMapper.insert(order) > 0) {
                    return AppletOrderResult().also {
                        it.orderId = order.id
                        it.payId = prepay_id
                    }
                }
            }
        }
        throw MallException(Code.BOOK_ORDER_FAIL)
    }


    /**
     * 查订单
     */
    fun bookOrderNotify(xml:String):String{
        //todo
        return ""
    }

    /**
     * 查订单
     */
    fun queryOrder(orderId:Long){
        //todo
    }
}