package com.github.luoyemyy.mall.core.service

import com.github.luoyemyy.mall.core.bean.OrderItem
import com.github.luoyemyy.mall.core.bean.PostageBean
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.OrderDao
import com.github.luoyemyy.mall.core.dao.PostageDao
import com.github.luoyemyy.mall.core.mapper.OrderMapper
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService {


    @Autowired
    private lateinit var orderMapper: OrderMapper
    @Autowired
    private lateinit var orderDao: OrderDao
    @Autowired
    private lateinit var batchDao: BatchDao

    /**
     * @admin
     */
    fun list(stateId: Int, page: Int): List<OrderItem> {
        return orderDao.getOrderItemByStatePage(stateId, page.toPageStart()) ?: listOf()
    }

}