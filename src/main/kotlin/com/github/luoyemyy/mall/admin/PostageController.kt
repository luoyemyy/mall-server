@file:Suppress("unused")

package com.github.luoyemyy.mall.admin

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.apiResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.admin.bean.PostageBean
import com.github.luoyemyy.mall.core.admin.PostageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-邮费"])
@RestController
@RequestMapping("admin/postage")
class PostageController : BaseController() {

    @Autowired
    private lateinit var postageService: PostageService

    /**
     *
     */
    @ApiOperation("列表")
    @RequestAdmin
    @GetMapping("list")
    fun list(): ListResponse<PostageBean> {
        return listResponse(postageService.list())
    }

    /**
     *
     */
    @ApiOperation("编辑")
    @RequestAdmin
    @PostMapping("edit")
    fun edit(@RequestBody list:List<PostageBean>): ApiResponse {
        return apiResponse(postageService.edit(list))
    }


}