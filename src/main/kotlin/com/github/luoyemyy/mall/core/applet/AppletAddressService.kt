package com.github.luoyemyy.mall.core.applet

import com.github.luoyemyy.mall.core.applet.bean.AppletAddress
import com.github.luoyemyy.mall.core.dao.AddressDao
import com.github.luoyemyy.mall.core.entity.UserAddress
import com.github.luoyemyy.mall.core.entity.UserAddressExample
import com.github.luoyemyy.mall.core.mapper.AddressMapper
import com.github.luoyemyy.mall.core.mapper.UserAddressMapper
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

    @Transactional
    fun getDefault(userId: Long): AppletAddress? {
       return addressDao.selectDefaultAddressByUser(userId)
    }



}