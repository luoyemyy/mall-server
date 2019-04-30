package com.github.luoyemyy.mall.core.admin

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.admin.bean.OrderDetail
import com.github.luoyemyy.mall.core.admin.bean.OrderItem
import com.github.luoyemyy.mall.core.dao.OrderDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.core.wx.WxPayService
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService {


    @Autowired
    private lateinit var orderDao: OrderDao
    @Autowired
    private lateinit var productDao: ProductDao
    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var wxPayService: WxPayService

    /**
     * @admin
     */
    fun list(stateId: Int, page: Int): List<OrderItem> {
        return orderDao.getOrderItemByStatePage(stateId, page.toPageStart()) ?: listOf()
    }

    /**
     * @admin
     */
    fun detail(orderId: Long): OrderDetail {
        return orderDao.selectOrderDetail(orderId)?.apply {
            products = productDao.selectOrderProducts(orderId)
        } ?: throw MallException(Code.ORDER_NOT_EXIST)
    }

    /**
     * 0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成
     * 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     */
    @Transactional
    fun state(orderId: Long, expressCompany: String?, expressNo: String?, refundMoney: Float): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        return when (order.state) {
            1 -> wxPayService.queryOrder(order, true)
            2 -> toDeliver(order)
            3 -> toTransport(order, expressCompany, expressNo)
            4 -> toSign(order)
            6 -> applyCancel(order, refundMoney)
            7 -> applyBack(order)
            8 -> back(order, refundMoney)
            9 -> refund(order)
            else -> false
        }
    }

    private fun toDeliver(order: Order): Boolean {
        order.state = 3
        return orderMapper.updateByPrimaryKeySelective(order) > 0
    }

    private fun toTransport(order: Order, expressCompany: String?, expressNo: String?): Boolean {
        order.state = 4
        order.expressCompany = expressCompany
        order.expressNo = expressNo
        order.deliverTime = Date()
        return orderMapper.updateByPrimaryKeySelective(order) > 0
    }

    private fun toSign(order: Order): Boolean {
        order.state = 5
        order.signTime = Date()
        return orderMapper.updateByPrimaryKeySelective(order) > 0
    }

    /**
     * （审核通过/退货成功）开始退款
     */
    private fun applyCancel(order: Order, refundMoney: Float): Boolean {
        val apply = if (order.wxOrderId.isNullOrEmpty()) {
            if (wxPayService.queryOrder(order, false)) {
                wxPayService.refund(order, refundMoney)
            } else {
                true
            }
        } else {
            wxPayService.refund(order, refundMoney)
        }
        if (apply) {
            order.state = 9
            order.refundMoney = refundMoney
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        }
        return false
    }

    private fun applyBack(order: Order): Boolean {
        order.state = 8
        return orderMapper.updateByPrimaryKeySelective(order) > 0
    }

    /**
     * 退货成功，开始退款
     */
    private fun back(order: Order, refundMoney: Float): Boolean {
        if (wxPayService.refund(order, refundMoney)) {
            order.state = 9
            order.refundMoney = refundMoney
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        }
        return false
    }

    /**
     *  查询退款结果
     */
    private fun refund(order: Order): Boolean {
        if (wxPayService.queryRefund(order)) {
            order.state = 10
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        }
        return false
    }
}