@file:Suppress("unused")

package com.github.luoyemyy.mall.applet

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.bean.AppletHotBean
import com.github.luoyemyy.mall.core.service2.AppletHotService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-推荐"])
@RestController
@RequestMapping("applet/hot")
class AppletHotController : BaseController() {

    @Autowired
    private lateinit var appletHotService: AppletHotService

    /**
     *
     */
    @ApiOperation("推荐列表")
    @RequestApplet
    @GetMapping("list")
    fun list(): ListResponse<AppletHotBean> {
        return listResponse(appletHotService.list())
    }
}