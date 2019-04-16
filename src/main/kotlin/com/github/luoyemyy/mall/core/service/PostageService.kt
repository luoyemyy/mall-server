package com.github.luoyemyy.mall.core.service

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.CategoryBean
import com.github.luoyemyy.mall.core.bean.PostageBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.CategoryDao
import com.github.luoyemyy.mall.core.dao.PostageDao
import com.github.luoyemyy.mall.core.entity.Category
import com.github.luoyemyy.mall.core.entity.CategoryExample
import com.github.luoyemyy.mall.core.entity.ProductCategoryExample
import com.github.luoyemyy.mall.core.mapper.CategoryMapper
import com.github.luoyemyy.mall.core.mapper.ProductCategoryMapper
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