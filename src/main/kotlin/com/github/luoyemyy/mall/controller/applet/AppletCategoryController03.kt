@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.applet.AppletCategoryService
import com.github.luoyemyy.mall.core.service.applet.bean.AppletCategoryBean
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-分类"])
@RestController
@RequestMapping("applet/category")
class AppletCategoryController03 : BaseController() {

    @Autowired
    private lateinit var appletCategoryService: AppletCategoryService

    @AppApi(pathId = 20301, auth = false)
    @ApiOperation("分类列表")
    @PostMapping("list")
    fun list(): ListResponse<AppletCategoryBean> {
        return listResponse(appletCategoryService.list())
    }

}