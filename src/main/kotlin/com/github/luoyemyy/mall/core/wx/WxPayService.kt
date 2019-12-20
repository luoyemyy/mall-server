package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.common.properties.AppletInfo
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrderProduct
import com.github.luoyemyy.mall.core.applet.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.WeChatDao
import com.github.luoyemyy.mall.core.entity.Address
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.entity.OrderExample
import com.github.luoyemyy.mall.core.entity.ProductExample
import com.github.luoyemyy.mall.core.mapper.AddressMapper
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.core.mapper.ProductMapper
import com.github.luoyemyy.mall.core.applet.AppletPostageService
import com.github.luoyemyy.mall.core.wx.bean.*
import com.github.luoyemyy.mall.util.newOrderNo
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

    /**
     * 金额单位：元->分
     */
    private fun moneyFormat(money: Float): Int {
        return (money * 100).toInt()
    }


    /**
     * 检查下订单用户
     */
    private fun bookOrderCheckUser(userId: Long): String {
        val weChat = weChatDao.selectByUser(userId) ?: throw AppException(AppCode.BOOK_ORDER_USER_ERROR)
        return weChat.openId ?: throw AppException(AppCode.BOOK_ORDER_USER_ERROR)
    }

    /**
     * 检查订单邮费
     */
    private fun bookOrderCheckPostage(productMoney: Float, postage: Float, addressId: Long): Float {
        val match = appletPostageService.match(addressId)
        if (match != null && match.post == 1) {
            val free = appletPostageService.free()
            if ((free > 0f && productMoney >= free && postage == 0f) || (free == 0f && postage == match.price)) {
                return postage
            }
        }
        throw AppException(AppCode.BOOK_ORDER_ADDRESS_ERROR)
    }

    /**
     * 检查订单的的产品
     */
    private fun bookOrderCheckProduct(products: List<AppletBookOrderProduct>?): Float {
        if (products.isNullOrEmpty()) throw AppException(AppCode.BOOK_ORDER_PRODUCT_ERROR)
        var orderMoney = 0f
        val orderProductDesc = products.sortedBy { it.productId }.joinToString(",") { "${it.productId}=${it.price}" }
        val productIds = products.map {
            orderMoney += it.count * it.price
            it.productId
        }
        if (productIds.isNullOrEmpty()) {
            throw AppException(AppCode.BOOK_ORDER_PRODUCT_ERROR)
        }
        val productDesc = productMapper.selectByExample(ProductExample().apply {
            createCriteria().andStatusEqualTo(1).andIdIn(productIds)
        })?.sortedBy { it.id }?.joinToString(",") {
            "${it.id}=${it.actualPrice}"
        }
        if (productDesc != orderProductDesc) {
            throw AppException(AppCode.BOOK_ORDER_PRODUCT_ERROR)
        }

        return orderMoney
    }

    /**
     * 检查订单的地址
     */
    private fun bookOrderCheckAddress(addressId: Long): Address {
        val address = addressMapper.selectByPrimaryKey(addressId) ?: throw AppException(AppCode.BOOK_ORDER_ADDRESS_ERROR)
        if (address.name.isNullOrEmpty() || address.phone.isNullOrEmpty() || address.summary.isNullOrEmpty() || address.postCode.isNullOrEmpty()) {
            throw AppException(AppCode.BOOK_ORDER_ADDRESS_ERROR)
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
            throw AppException(AppCode.BOOK_ORDER_MONEY_ERROR)
        }
        val productCount = appletOrder.products?.map { it.count }?.sum() ?: 0
        //生成订单
        val order = Order().apply {
            this.orderNo = newOrderNo()
            this.userId = userId
            this.state = 0
            this.money = appletOrder.money
            this.productCount = productCount
            this.postage = postage
            this.username = address.name
            this.phone = address.phone
            this.address = address.summary
            this.postcode = address.postCode
            this.createTime = Date()
            this.wxPayId = null
            this.wxOrderId = null
            this.status = 1
        }

        //直接返回订单注册成功，模拟微信支付
        if (appletInfo.payMock) {
            return mockWxPay.bookOrder(order, appletOrder)
        }

        val bookRequest = BookOrderRequest(appletInfo, order.orderNo, moneyFormat(appletOrder.money), openId)
        //注册订单
        httpService.postXml(URL_BOOK_ORDER, bookRequest.toXml())?.apply {
            val response = BookOrderResponse(this)
            if (response.success(appletInfo.mchKey)) {
                order.wxPayId = response.getPayId()
                if (orderMapper.insert(order) > 0) {
                    if (batchDao.insertOrderProduct(order.id, appletOrder.products)) {
                        return AppletBookOrderResult().also {
                            it.orderId = order.id
                            it.payId = order.wxPayId
                            it.buildParams()
                        }
                    }
                }
            }
        }
        throw AppException(AppCode.BOOK_ORDER_FAIL)
    }

    /**
     * 用户重新获得支付的参数
     */
    fun bookPayRetry(userId: Long, orderId: Long): AppletBookOrderResult {
        val order = orderMapper.selectByExample(OrderExample().apply {
            createCriteria().andUserIdEqualTo(userId).andIdEqualTo(orderId)
        })?.firstOrNull() ?: throw AppException(AppCode.ORDER_NOT_EXIST)
        order.createTime?.apply {
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
        throw AppException(AppCode.ORDER_CANCELED)
    }


    /**
     * 通过订单编号产查询订单
     */
    fun getByOrderNo(orderNo: String?): Order? {
        return orderMapper.selectByExample(OrderExample().apply {
            createCriteria().andStatusEqualTo(1).andOrderNoEqualTo(orderNo)
        })?.firstOrNull()
    }


    /**
     * 支付结果通知
     */
    fun bookOrderNotify(xml: String): String {
        NotifyOrderResponse(xml).apply {
            if (success(appletInfo.mchKey)) {
                getByOrderNo(getOrderNo())?.also {
                    if (it.state == 1) {//订单在待确认状态下，接收到支付成功通知，此时转换状态为已支付成功
                        it.state = 2
                        it.wxOrderId = getWxOrderId()
                        it.payTime = Date()
                        orderMapper.updateByPrimaryKeySelective(it) > 0
                    }
                }
            }
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>"
    }

    /**
     * 订单逻辑
     * 状态：
     * 0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     * 状态流转：
     * 0-> 客户支付 1；客户取消 10
     * 1-> 商户确认支付 2；客户取消 6
     * 2-> 商户备货 3
     * 3-> 商户发货 4
     * 4-> 客户确认收货 5
     * 5-> 客户申请退货 7
     * 6-> 商户审核退款 9
     * 7-> 商户审核退货 8
     * 8-> 商户确认已退货 9
     * 9-> 商户确认已退款 10
     *
     */

    /**
     * 查订单
     */
    @Transactional
    fun queryOrder(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: return false
        if (order.userId != userId || order.status == 0) {
            return false
        }
        if (order.state == 2) {
            return true
        }
        //用户只有订单状态在 0 1 10 的时候才可以查询订单支付结果
        if (order.state in listOf(0, 1, 10)) {
            //模拟查询微信支付结果
            return queryOrder(order, true)
        }
        return false
    }

    /**
     * 查订单
     */
    @Transactional
    fun queryOrder(order: Order, updateState: Boolean): Boolean {
        //模拟查询微信支付结果
        if (appletInfo.payMock) {
            return mockWxPay.queryOrder(order, updateState)
        }

        val queryOrderRequest = QueryOrderRequest(appletInfo, order.orderNo)
        httpService.postXml(URL_QUERY_ORDER, queryOrderRequest.toXml())?.apply {
            val queryOrderResponse = QueryOrderResponse(this)
            if (queryOrderResponse.success(appletInfo.mchKey)) {
                if (updateState) {
                    order.state = 2
                    order.payTime = Date()
                }
                order.wxOrderId = queryOrderResponse.getWxOrderId()
                return orderMapper.updateByPrimaryKeySelective(order) > 0
            }
        }
        return false
    }

    /**
     * 确认退款
     */
    fun refund(order: Order, refundMoney: Float): Boolean {
        order.refuseOrderNo = newOrderNo()
        if (appletInfo.payMock) {
            return mockWxPay.refund(order)
        }

        val refundRequest = RefundRequest(appletInfo, order.wxOrderId, order.refuseOrderNo, moneyFormat(order.money), moneyFormat(order.refundMoney))
        httpService.postXml(URL_REFUND_ORDER, refundRequest.toXml())?.apply {
            val refundResponse = RefundResponse(this)
            if (refundResponse.success(appletInfo.mchKey)) {
                order.refuseWxNo = refundResponse.getWxRefundNo()
                return true
            }
        }
        return false
    }

    /**
     * 查询退款结果
     */
    fun queryRefund(order: Order): Boolean {
        if (appletInfo.payMock) {
            return mockWxPay.queryRefund(order)
        }

        val queryRefundRequest = QueryRefundRequest(appletInfo, order.wxOrderId)
        httpService.postXmlUseCert(URL_QUERY_REFUND_ORDER, queryRefundRequest.toXml())?.apply {
            val queryOrderResponse = QueryRefundResponse(this)
            if (queryOrderResponse.success(appletInfo.mchKey)) {
                return true
            }
        }
        return false
    }


    /**
     * 退款结果通知
     */
    @Transactional
    fun refundNotify(xml: String): String {
        NotifyRefundResponse(xml).apply {
            if (success(appletInfo.mchKey)) {
                getByOrderNo(getOrderNo())?.also {
                    if (it.state == 9) {//订单在退款中，接收到退款成功通知，此时转换状态为已取消
                        it.state = 10
                        orderMapper.updateByPrimaryKeySelective(it) > 0
                    }
                }
            }
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>"
    }
}