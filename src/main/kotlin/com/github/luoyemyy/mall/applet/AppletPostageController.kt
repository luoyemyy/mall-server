@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.core.bean.CategoryBean
import com.github.luoyemyy.mall.core.bean.PostageBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.service.CategoryService
import com.github.luoyemyy.mall.core.service.PostageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["微信-邮费"])
@RestController
@RequestMapping("applet/postage")
class AppletPostageController : BaseController() {

    @Autowired
    private lateinit var postageService: PostageService

    /**
     *
     */
    @ApiOperation("列表")
    @RequestAdmin
    @GetMapping("list")
    fun list(): ListResponse<PostageBean> {
        return listResponse(postageService.validList())
    }

}