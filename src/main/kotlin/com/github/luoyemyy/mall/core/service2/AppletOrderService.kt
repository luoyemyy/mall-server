package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.AppletOrderIndex
import com.github.luoyemyy.mall.core.bean.AppletOrderInfo
import com.github.luoyemyy.mall.core.bean.AppletOrderItem
import com.github.luoyemyy.mall.core.bean.AppletOrderProduct
import com.github.luoyemyy.mall.core.dao.OrderDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.entity.OrderExample
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AppletOrderService {

    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var orderDao: OrderDao
    @Autowired
    private lateinit var productDao: ProductDao

    /**
     * 订单逻辑
     * 状态：
     * 0 未支付 1 已支付，待确认 2 支付成功，待发货 3 运输中 4 已签收，交易完成 5 取消订单，待审核  6 退货，待审核 7 退货中 8 退款中 9 已取消
     * 状态流转：
     * 0 客户支付 1；客户取消 9
     * 1 商户确认支付 2；客户取消 5
     * 2 商户发货 3；客户取消 5
     * 3 客户确认收货 4
     * 4 客户申请退货 6
     * 5 商户审核退款 8
     * 6 商户审核退货 7
     * 7 商户确认已退货 8
     * 8 商户确认已退款 9
     *
     */

    /**
     * @applet
     */
    @Transactional
    fun index(userId: Long): AppletOrderIndex {
        //更新超时未支付订单状态为已取消9
        updateTimeout(userId)
        return AppletOrderIndex().apply {
            pay = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateEqualTo(0)
            })
            accept = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateIn(listOf(1, 2, 3))
            })
            refuse = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateIn(listOf(5, 6, 7, 8))
            })
        }
    }

    private fun updateTimeout(userId: Long) {
        val before30min = Date(System.currentTimeMillis() - 30 * 60 * 1000)
        orderMapper.updateByExampleSelective(Order().apply {
            state = 9
            updateTime = Date()
        }, OrderExample().apply {
            createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateEqualTo(0).andUpdateTimeLessThanOrEqualTo(before30min)
        })
    }

    /**
     * @param type 1 待支付 2 待收货 3 退款、退货 4 全部
     */
    @Transactional
    fun list(userId: Long, type: Int): List<AppletOrderItem> {
        val state = when (type) {
            1 -> listOf(0)
            2 -> listOf(1, 2, 3)
            3 -> listOf(5, 6, 7, 8)
            else -> listOf()
        }
        updateTimeout(userId)
        return if (type == 4) {
            orderDao.selectOrderItemAll(userId)
        } else {
            orderDao.selectOrderItemByState(userId, state.joinToString(","))
        }?.apply {
            forEach { it.products = getOrderProducts(it.id) }
        } ?: listOf()
    }

    fun info(userId: Long, orderId: Long): AppletOrderInfo {
        return orderDao.selectOrderInfo(userId, orderId)?.apply {
            products = getOrderProducts(orderId)
        } ?: throw MallException(Code.ORDER_NOT_EXIST)
    }

    private fun getOrderProducts(orderId: Long): List<AppletOrderProduct>? {
        return productDao.selectOrderProducts(orderId)
    }

    fun delete(userId: Long, orderId: Long): Boolean {
        return orderMapper.updateByExampleSelective(Order().apply {
            status = 0
        }, OrderExample().apply {
            createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId)
        }) > 0
    }

    @Transactional
    fun cancel(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            0 -> {
                order.state = 9
                true
            }
            1, 2 -> {
                order.state = 5
                true
            }
            else -> false
        }
        return if (ok) {
            order.updateTime = Date()
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        } else false
    }

    @Transactional
    fun accept(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            3 -> {
                order.state = 4
                true
            }
            else -> false
        }
        return if (ok) {
            order.updateTime = Date()
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        } else false
    }

    @Transactional
    fun back(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            4 -> {
                order.state = 6
                true
            }
            else -> false
        }
        return if (ok) {
            order.updateTime = Date()
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        } else false
    }


}