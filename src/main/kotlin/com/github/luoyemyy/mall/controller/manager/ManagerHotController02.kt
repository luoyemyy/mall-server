@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.admin.HotService
import com.github.luoyemyy.mall.core.service.admin.bean.HotBean
import com.github.luoyemyy.mall.core.service.admin.bean.SortBean
import com.github.luoyemyy.mall.util.toList
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-推荐"])
@RestController
@RequestMapping("admin/hot")
class ManagerHotController02 : BaseController() {

    @Autowired
    private lateinit var hotService: HotService

    @AppApi(pathId = 10201)
    @ApiOperation("推荐列表")
    
    @GetMapping("list")
    fun list(): ListResponse<HotBean> {
        return listResponse(hotService.list())
    }

    @AppApi(pathId = 10202)
    @ApiOperation("新增推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "image", value = "封面地址", paramType = "body", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "description", value = "描述", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    
    @PostMapping("add")
    fun add(image: String, description: String): ApiResponse {
        return apiResponse(hotService.add(image, description))
    }

    @AppApi(pathId = 10203)
    @ApiOperation("新增推荐产品")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "productIds", value = "产品id JsonArrayString 例：[1,3,5,6]", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    
    @PostMapping("add/product")
    fun addProduct(id: Long, productIds: String): ApiResponse {
        val ids = productIds.toList<Long>() ?: throw AppException(AppCode.INVALID_PARAM)
        return apiResponse(hotService.addProduct(id, ids))
    }

    @AppApi(pathId = 10204)
    @ApiOperation("删除推荐产品")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "productId", value = "产品id ", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    
    @PostMapping("delete/product")
    fun deleteProduct(id: Long, productId: Long): ApiResponse {
        return apiResponse(hotService.deleteProduct(id, productId))
    }

    @AppApi(pathId = 10205)
    @ApiOperation("编辑推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "image", value = "封面地址", paramType = "body", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "description", value = "描述", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    
    @PostMapping("edit")
    fun edit(id: Long, image: String, description: String): ApiResponse {
        return apiResponse(hotService.edit(id, image, description))
    }

    @AppApi(pathId = 10206)
    @ApiOperation("推荐是否有效")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "state", value = "是否有效 1 有效 0 无效", paramType = "body", required = true, dataTypeClass = Int::class)
    ])
    
    @PostMapping("state")
    fun state(id: Long, state: Int): ApiResponse {
        return apiResponse(hotService.state(id, state))
    }

    @AppApi(pathId = 10207)
    @ApiOperation("删除推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(hotService.delete(id))
    }

    @AppApi(pathId = 10208)
    @ApiOperation("推荐排序")
    
    @PostMapping("sort")
    fun sort(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(hotService.sort(sort))
    }

}