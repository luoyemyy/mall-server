@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.applet

import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestApplet
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.applet.bean.AppletHotBean
import com.github.luoyemyy.mall.core.applet.AppletHotService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["微信-推荐"])
@RestController
@RequestMapping("applet/hot")
class AppletHotController04 : BaseController() {

    @Autowired
    private lateinit var appletHotService: AppletHotService

    @AppApi(pathId = 20401, auth = false)
    @ApiOperation("推荐列表")
    @GetMapping("list")
    fun list(): ListResponse<AppletHotBean> {
        return listResponse(appletHotService.list())
    }
}