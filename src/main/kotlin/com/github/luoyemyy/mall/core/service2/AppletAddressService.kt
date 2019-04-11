package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.core.bean.AppletAddress
import com.github.luoyemyy.mall.core.bean.AppletCart
import com.github.luoyemyy.mall.core.bean.AppletHotBean
import com.github.luoyemyy.mall.core.dao.AddressDao
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.*
import com.github.luoyemyy.mall.core.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AppletAddressService {

    @Autowired
    private lateinit var addressDao: AddressDao
    @Autowired
    private lateinit var addressMapper: AddressMapper
    @Autowired
    private lateinit var userAddressMapper: UserAddressMapper

    fun list(userId: Long): List<AppletAddress> {
        return addressDao.selectAddressByUser(userId) ?: listOf()
    }

    @Transactional
    fun add(userId: Long, appletAddress: AppletAddress): Boolean {
        val address = appletAddress.toAddress()
        if (addressMapper.insert(address) > 0) {
            userAddressMapper.insert(UserAddress().apply {
                this.userId = userId
                this.addressId = address.id
                this.type = 0
            })
            return true
        }
        return false
    }

    @Transactional
    fun delete(userId: Long, id: Long): Boolean {
        if (userAddressMapper.deleteByExample(UserAddressExample().apply { createCriteria().andAddressIdEqualTo(id).andUserIdEqualTo(userId) }) > 0) {
            addressMapper.deleteByPrimaryKey(id)
        }
        return true
    }

    @Transactional
    fun setDefault(userId: Long, id: Long): Boolean {
        userAddressMapper.updateByExampleSelective(UserAddress().apply {
            type = 0
        }, UserAddressExample().apply {
            createCriteria().andUserIdEqualTo(userId)
        })
        userAddressMapper.updateByExampleSelective(UserAddress().apply {
            type = 1
        }, UserAddressExample().apply {
            createCriteria().andAddressIdEqualTo(id).andUserIdEqualTo(userId)
        })
        return true
    }


}