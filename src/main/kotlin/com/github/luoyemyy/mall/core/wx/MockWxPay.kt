package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.AppletOrder
import com.github.luoyemyy.mall.core.bean.AppletOrderResult
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.entity.Order
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.util.newOrderNo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MockWxPay {
    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var batchDao: BatchDao

    fun bookOrder(order: Order,appletOrder: AppletOrder):AppletOrderResult{
        order.wxPayId = newOrderNo()
        if (orderMapper.insert(order) > 0) {
            if (batchDao.insertOrderProduct(order.id, appletOrder.products)) {
                return AppletOrderResult().also {
                    it.orderId = order.id
                    it.payId = order.wxPayId
                    it.mock = true
                }
            }
        }
        throw MallException(Code.BOOK_ORDER_FAIL)
    }
}