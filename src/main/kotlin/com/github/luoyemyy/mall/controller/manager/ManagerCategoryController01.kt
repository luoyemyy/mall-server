@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.admin.CategoryService
import com.github.luoyemyy.mall.controller.manager.response.ResponseManagerCategory
import com.github.luoyemyy.mall.core.service.admin.bean.SortBean
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-分类"])
@RestController
@RequestMapping("admin/category")
class ManagerCategoryController01 : BaseController() {

    @Autowired
    private lateinit var categoryService: CategoryService

    @AppApi(pathId = 10101)
    @ApiOperation("分类列表")
    @GetMapping("list")
    fun list(): ListResponse<ResponseManagerCategory> {
        return listResponse(categoryService.allList())
    }

    @AppApi(pathId = 10102)
    @ApiOperation("有效分类列表")
    @GetMapping("list/valid")
    fun validList(): ListResponse<ResponseManagerCategory> {
        return listResponse(categoryService.validList())
    }

    @AppApi(pathId = 10103)
    @ApiOperation("新增分类")
    @ApiImplicitParam(name = "name", value = "分类名称", paramType = "body", required = true, dataTypeClass = String::class)
    @PostMapping("add")
    fun add(name: String): ApiResponse {
        return apiResponse(categoryService.add(name))
    }

    @AppApi(pathId = 10104)
    @ApiOperation("编辑分类")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataType = "Long", dataTypeClass = Long::class),
        ApiImplicitParam(name = "name", value = "分类名称", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    @PostMapping("edit")
    fun edit(id: Long, name: String): ApiResponse {
        return apiResponse(categoryService.edit(id, name))
    }

    @AppApi(pathId = 10105)
    @ApiOperation("分类是否有效")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "state", value = "是否有效 1 有效 0 无效", paramType = "body", required = true, dataTypeClass = Int::class)
    ])
    @PostMapping("state")
    fun state(id: Long, state: Int): ApiResponse {
        return apiResponse(categoryService.state(id, state))
    }

    @AppApi(pathId = 10106)
    @ApiOperation("删除分类")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(categoryService.delete(id))
    }

    @AppApi(pathId = 10107)
    @ApiOperation("分类排序")
    @PostMapping("sort")
    fun sort(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(categoryService.sort(sort))
    }

}