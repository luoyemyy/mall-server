@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.admin.PostageService
import com.github.luoyemyy.mall.core.service.admin.bean.PostageBean
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-邮费"])
@RestController
@RequestMapping("admin/postage")
class ManagerPostageController05 : BaseController() {

    @Autowired
    private lateinit var postageService: PostageService

    @AppApi(pathId = 10501)
    @ApiOperation("列表")
   
    @GetMapping("list")
    fun list(): ListResponse<PostageBean> {
        return listResponse(postageService.list())
    }

    @AppApi(pathId = 10502)
    @ApiOperation("编辑")
   
    @PostMapping("edit")
    fun edit(@RequestBody list:List<PostageBean>): ApiResponse {
        return apiResponse(postageService.edit(list))
    }


}