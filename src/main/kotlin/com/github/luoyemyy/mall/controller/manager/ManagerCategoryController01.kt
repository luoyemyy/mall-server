@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.controller.response.*
import com.github.luoyemyy.mall.core.admin.bean.CategoryBean
import com.github.luoyemyy.mall.core.admin.bean.SortBean
import com.github.luoyemyy.mall.core.admin.CategoryService
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

    /**
     *
     */
    @ApiOperation("分类列表")
    @RequestAdmin
    @GetMapping("list")
    fun list(): ListResponse<CategoryBean> {
        return listResponse(categoryService.allList())
    }

    /**
     *
     */
    @ApiOperation("有效分类列表")
    @RequestAdmin
    @GetMapping("list/valid")
    fun validList(): ListResponse<CategoryBean> {
        return listResponse(categoryService.validList())
    }

    /**
     *
     */
    @ApiOperation("新增分类")
    @ApiImplicitParam(name = "name", value = "分类名称", paramType = "body", required = true, dataTypeClass = String::class)
    @RequestAdmin
    @PostMapping("add")
    fun add(name: String): ApiResponse {
        return apiResponse(categoryService.add(name))
    }

    /**
     *
     */
    @ApiOperation("编辑分类")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataType = "Long", dataTypeClass = Long::class),
        ApiImplicitParam(name = "name", value = "分类名称", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    @RequestAdmin
    @PostMapping("edit")
    fun edit(id: Long, name: String): ApiResponse {
        return apiResponse(categoryService.edit(id, name))
    }

    /**
     *
     */
    @ApiOperation("分类是否有效")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "state", value = "是否有效 1 有效 0 无效", paramType = "body", required = true, dataTypeClass = Int::class)
    ])
    @RequestAdmin
    @PostMapping("state")
    fun state(id: Long, state: Int): ApiResponse {
        return apiResponse(categoryService.state(id, state))
    }

    /**
     *
     */
    @ApiOperation("删除分类")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "分类id", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    @RequestAdmin
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(categoryService.delete(id))
    }

    /**
     *
     */
    @ApiOperation("分类排序")
    @RequestAdmin
    @PostMapping("sort")
    fun sort(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(categoryService.sort(sort))
    }

}