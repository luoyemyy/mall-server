package com.github.luoyemyy.mall.core.service.admin

import com.github.luoyemyy.mall.core.service.admin.bean.PostageBean
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.PostageDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostageService {



    @Autowired
    private lateinit var postageDao: PostageDao
    @Autowired
    private lateinit var batchDao: BatchDao

    /**
     * @admin
     */
    fun list(): List<PostageBean> {
        return postageDao.selectAll()?: listOf()
    }

    /**
     * @admin
     */
    @Transactional
    fun edit(list: List<PostageBean>): Boolean {
        return batchDao.updatePostage(list)
    }
}