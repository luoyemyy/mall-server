@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.core.bean.AppletCategoryBean
import com.github.luoyemyy.mall.core.bean.CategoryBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.service.CategoryService
import com.github.luoyemyy.mall.core.service2.AppletCategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-分类"])
@RestController
@RequestMapping("applet/category")
class AppletCategoryController : BaseController() {

    @Autowired
    private lateinit var appletCategoryService: AppletCategoryService

    /**
     *
     */
    @ApiOperation("分类列表")
    @RequestApplet(needLogin = false)
    @GetMapping("list")
    fun list(): ListResponse<AppletCategoryBean> {
        return listResponse(appletCategoryService.list())
    }

}