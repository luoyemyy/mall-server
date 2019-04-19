package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.core.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.bean.AppletBookOrderProduct
import com.github.luoyemyy.mall.core.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.WeChatDao
import com.github.luoyemyy.mall.core.entity.Address
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.entity.OrderExample
import com.github.luoyemyy.mall.core.entity.ProductExample
import com.github.luoyemyy.mall.core.mapper.AddressMapper
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.core.mapper.ProductMapper
import com.github.luoyemyy.mall.core.service.HttpService
import com.github.luoyemyy.mall.core.service2.AppletPostageService
import com.github.luoyemyy.mall.core.wx.bean.BookOrder
import com.github.luoyemyy.mall.core.wx.bean.BookOrderResult
import com.github.luoyemyy.mall.util.newOrderNo
import com.github.luoyemyy.mall.util.xmlToObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
    private lateinit var appletPostageService: AppletPostageService
    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var productMapper: ProductMapper
    @Autowired
    private lateinit var batchDao: BatchDao
    @Autowired
    private lateinit var mockWxPay: MockWxPay
    @Autowired
    private lateinit var addressMapper: AddressMapper


    private fun bookOrderCheckUser(userId: Long): String {
        val weChat = weChatDao.selectByUser(userId) ?: throw MallException(Code.BOOK_ORDER_USER_ERROR)
        return weChat.openId ?: throw MallException(Code.BOOK_ORDER_USER_ERROR)
    }

    private fun bookOrderCheckPostage(productMoney: Float, postage: Float, addressId: Long): Float {
        val match = appletPostageService.match(addressId)
        if (match != null && match.post == 1) {
            val free = appletPostageService.free()
            if ((free > 0f && productMoney >= free && postage == 0f) || (free == 0f && postage == match.price)) {
                return postage
            }
        }
        throw MallException(Code.BOOK_ORDER_ADDRESS_ERROR)
    }

    private fun bookOrderCheckProduct(products: List<AppletBookOrderProduct>?): Float {
        if (products.isNullOrEmpty()) throw MallException(Code.BOOK_ORDER_PRODUCT_ERROR)
        var orderMoney = 0f
        val orderProductDesc = products.joinToString(",") { "${it.productId}=${it.price}" }
        val productIds = products.map {
            orderMoney += it.count * it.price
            it.productId
        }
        if (productIds.isNullOrEmpty()) {
            throw MallException(Code.BOOK_ORDER_PRODUCT_ERROR)
        }
        val productDesc = productMapper.selectByExample(ProductExample().apply {
            createCriteria().andStatusEqualTo(1).andIdIn(productIds)
        })?.joinToString(",") {
            "${it.id}=${it.actualPrice}"
        }
        if (productDesc != orderProductDesc) {
            throw MallException(Code.BOOK_ORDER_PRODUCT_ERROR)
        }

        return orderMoney
    }

    private fun bookOrderCheckAddress(addressId: Long): Address {
        val address = addressMapper.selectByPrimaryKey(addressId) ?: throw MallException(Code.BOOK_ORDER_ADDRESS_ERROR)
        if (address.name.isNullOrEmpty() || address.phone.isNullOrEmpty() || address.summary.isNullOrEmpty() || address.postCode.isNullOrEmpty()) {
            throw MallException(Code.BOOK_ORDER_ADDRESS_ERROR)
        }
        return address
    }

    /**
     * 下单
     */
    fun bookOrder(userId: Long, appletOrder: AppletBookOrder): AppletBookOrderResult {
        //检验用户
        val openId = bookOrderCheckUser(userId)
        //检验地址
        val address = bookOrderCheckAddress(appletOrder.addressId)
        //检验产品和价格
        val orderProductMoney = bookOrderCheckProduct(appletOrder.products)
        //检验邮费
        val postage = bookOrderCheckPostage(orderProductMoney, appletOrder.postage, appletOrder.addressId)
        //检验金额
        if (postage + orderProductMoney != appletOrder.money) {
            throw MallException(Code.BOOK_ORDER_MONEY_ERROR)
        }
        //生成订单
        val order = Order().apply {
            this.orderNo = newOrderNo()
            this.userId = userId
            this.state = 0
            this.money = appletOrder.money
            this.postage = postage
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
        val bookOrder = BookOrder(appletInfo, order.orderNo, (appletOrder.money * 100).toInt(), openId)

        //直接返回订单注册成功，模拟微信支付
        if (appletInfo.payMock) {
            return mockWxPay.bookOrder(order, appletOrder)
        }
        //注册订单
        httpService.postXml(URL_BOOK_ORDER, bookOrder.buildXml())?.xmlToObject<BookOrderResult>()?.apply {
            if (success(appletInfo.mchKey)) {
                order.wxPayId = prepay_id
                if (orderMapper.insert(order) > 0) {
                    if (batchDao.insertOrderProduct(order.id, appletOrder.products)) {
                        return AppletBookOrderResult().also {
                            it.orderId = order.id
                            it.payId = prepay_id
                            it.buildParams()
                        }
                    }
                }
            }
        }
        throw MallException(Code.BOOK_ORDER_FAIL)
    }

    @Transactional
    fun bookPaySuccess(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByExample(OrderExample().apply {
            createCriteria().andUserIdEqualTo(userId).andIdEqualTo(orderId)
        })?.firstOrNull() ?: throw MallException(Code.ORDER_NOT_EXIST)
        order.updateTime = Date()
        order.state = 1
        return orderMapper.updateByPrimaryKeySelective(order) > 0
    }

    fun bookPayRetry(userId: Long, orderId: Long): AppletBookOrderResult {
        val order = orderMapper.selectByExample(OrderExample().apply {
            createCriteria().andUserIdEqualTo(userId).andIdEqualTo(orderId)
        })?.firstOrNull() ?: throw MallException(Code.ORDER_NOT_EXIST)
        order.updateTime?.apply {
            if (this.time + 30 * 60 * 1000 > System.currentTimeMillis()) {
                return if (appletInfo.payMock) {
                    mockWxPay.bookPay(order)
                } else {
                    AppletBookOrderResult().also {
                        it.orderId = order.id
                        it.payId = order.wxPayId
                        it.buildParams()
                    }
                }
            }
        }
        throw MallException(Code.ORDER_CANCELED)
    }

    /**
     * 查订单
     */
    fun bookOrderNotify(xml: String): String {
        //todo
        return ""
    }

    /**
     * 查订单
     */
    fun queryOrder(orderId: Long) {
        //todo
    }
}