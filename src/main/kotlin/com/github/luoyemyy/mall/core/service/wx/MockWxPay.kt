package com.github.luoyemyy.mall.core.service.wx

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.core.service.applet.bean.AppletBookOrder
import com.github.luoyemyy.mall.core.service.applet.bean.AppletBookOrderResult
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.util.newOrderNo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MockWxPay {
    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var batchDao: BatchDao

    fun bookOrder(order: Order, appletOrder: AppletBookOrder): AppletBookOrderResult {
        order.wxPayId = newOrderNo()
        if (orderMapper.insert(order) > 0) {
            if (batchDao.insertOrderProduct(order.id, appletOrder.products)) {
                return bookPay(order)
            }
        }
        throw AppException(AppCode.BOOK_ORDER_FAIL)
    }

    fun bookPay(order: Order): AppletBookOrderResult {
        return AppletBookOrderResult().also {
            it.orderId = order.id
            it.payId = order.wxPayId
            it.mock = true
            it.buildParams()
        }
    }

    fun queryOrder(order: Order, updateState: Boolean): Boolean {
        if (Math.random() > 0.5) {
            if (updateState) {
                order.state = 2
                order.payTime = Date()
            }
            order.wxOrderId = newOrderNo()
            return orderMapper.updateByPrimaryKeySelective(order) > 0
        }
        return false
    }

    fun refund(order: Order): Boolean {
        order.refuseWxNo = newOrderNo()
        return true
    }

    fun queryRefund(order: Order): Boolean {
        return true
    }
}