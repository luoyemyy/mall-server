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
import com.github.luoyemyy.mall.util.toPageStart
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
     * @applet
     */
    @Transactional
    fun index(userId: Long): AppletOrderIndex {
        //更新超时未支付订单状态为已取消10
        updateTimeout(userId)
        return AppletOrderIndex().apply {
            pay = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateEqualTo(0)
            })
            accept = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateIn(listOf(1, 2, 3, 4))
            })
            refuse = orderMapper.countByExample(OrderExample().apply {
                createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateIn(listOf(6, 7, 8, 9))
            })
        }
    }

    private fun updateTimeout(userId: Long) {
        val before30min = Date(System.currentTimeMillis() - 30 * 60 * 1000)
        orderMapper.updateByExampleSelective(Order().apply {
            state = 10
            updateTime = Date()
        }, OrderExample().apply {
            createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(1).andStateEqualTo(0).andUpdateTimeLessThanOrEqualTo(before30min)
        })
    }

    /**
     * @param type 1 待支付 2 待收货 3 退款、退货 4 全部
     */
    @Transactional
    fun list(userId: Long, type: Int, page: Int): List<AppletOrderItem> {
        val state = when (type) {
            1 -> listOf(0)
            2 -> listOf(1, 2, 3, 4)
            3 -> listOf(6, 7, 8, 9)
            else -> listOf()
        }
        updateTimeout(userId)
        return if (type == 4) {
            orderDao.selectOrderItemByPage(userId, page.toPageStart())
        } else {
            orderDao.selectOrderItemByStatePage(userId, state, page.toPageStart())
        }?.apply {
            forEach { it.products = getOrderProducts(it.id) }
        } ?: listOf()
    }

    fun info(userId: Long, orderId: Long): AppletOrderInfo {
        return orderDao.selectOrderInfo(userId, orderId)?.apply {
            products = getOrderProducts(orderId)
            if (state == 0) {
                payLimitTime = updateTime?.let {
                    Date(it.time + 30 * 60 * 1000)
                }
            }
        } ?: throw MallException(Code.ORDER_NOT_EXIST)
    }

    private fun getOrderProducts(orderId: Long): List<AppletOrderProduct>? {
        return productDao.selectOrderProducts(orderId)
    }

    @Transactional
    fun delete(userId: Long, orderId: Long): Boolean {
        return orderMapper.updateByExampleSelective(Order().apply {
            status = 0
        }, OrderExample().apply {
            createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId)
        }) > 0
    }

    @Transactional
    fun payComplete(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            0 -> {
                order.state = 1
                true
            }
            else -> false
        }
        return if (ok) {
            order.updateTime = Date()
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        } else false

    }

    /**
     *  0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     */
    @Transactional
    fun cancel(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            0 -> {
                order.state = 10
                true
            }
            1, 2 -> {
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

    /**
     *  0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     */
    @Transactional
    fun accept(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            4 -> {
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

    /**
     *  0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     */
    @Transactional
    fun back(userId: Long, orderId: Long): Boolean {
        val order = orderMapper.selectByPrimaryKey(orderId) ?: throw MallException(Code.ORDER_NOT_EXIST)
        if (order.userId != userId || order.status == 0) {
            throw MallException(Code.ORDER_NOT_EXIST)
        }
        val ok = when (order.state) {
            5 -> {
                order.state = 7
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