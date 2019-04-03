package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.core.bean.AppletCategoryBean
import com.github.luoyemyy.mall.core.entity.CategoryExample
import com.github.luoyemyy.mall.core.mapper.CategoryMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppletCategoryService {

    @Autowired
    private lateinit var categoryMapper: CategoryMapper

    /**
     * @applet
     */
    fun list(): List<AppletCategoryBean> {
        return categoryMapper.selectByExample(
                CategoryExample().apply {
                    createCriteria().andStateEqualTo(1)
                    orderByClause = "sort desc"
                })?.map { AppletCategoryBean.map(it) } ?: listOf()
    }

}