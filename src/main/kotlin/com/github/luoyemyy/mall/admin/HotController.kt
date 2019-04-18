@file:Suppress("unused")

package com.github.luoyemyy.mall.admin

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.ApiResponse
import com.github.luoyemyy.mall.base.response.ListResponse
import com.github.luoyemyy.mall.base.response.apiResponse
import com.github.luoyemyy.mall.base.response.listResponse
import com.github.luoyemyy.mall.core.bean.HotBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.service.HotService
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
class HotController : BaseController() {

    @Autowired
    private lateinit var hotService: HotService

    /**
     *
     */
    @ApiOperation("推荐列表")
    @RequestAdmin
    @GetMapping("list")
    fun list(): ListResponse<HotBean> {
        return listResponse(hotService.list())
    }

    /**
     *
     */
    @ApiOperation("新增推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "image", value = "封面地址", paramType = "body", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "description", value = "描述", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    @RequestAdmin
    @PostMapping("add")
    fun add(image: String, description: String): ApiResponse {
        return apiResponse(hotService.add(image, description))
    }

    /**
     *
     */
    @ApiOperation("新增推荐产品")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "productIds", value = "产品id JsonArrayString 例：[1,3,5,6]", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    @RequestAdmin
    @PostMapping("add/product")
    fun addProduct(id: Long, productIds: String): ApiResponse {
        val ids = productIds.toList<Long>() ?: throw MallException(Code.INVALID_PARAM)
        return apiResponse(hotService.addProduct(id, ids))
    }

    /**
     *
     */
    @ApiOperation("删除推荐产品")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "productId", value = "产品id ", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    @RequestAdmin
    @PostMapping("delete/product")
    fun deleteProduct(id: Long, productId: Long): ApiResponse {
        return apiResponse(hotService.deleteProduct(id, productId))
    }

    /**
     *
     */
    @ApiOperation("编辑推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "image", value = "封面地址", paramType = "body", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "description", value = "描述", paramType = "body", required = true, dataTypeClass = String::class)
    ])
    @RequestAdmin
    @PostMapping("edit")
    fun edit(id: Long, image: String, description: String): ApiResponse {
        return apiResponse(hotService.edit(id, image, description))
    }

    /**
     *
     */
    @ApiOperation("推荐是否有效")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "state", value = "是否有效 1 有效 0 无效", paramType = "body", required = true, dataTypeClass = Int::class)
    ])
    @RequestAdmin
    @PostMapping("state")
    fun state(id: Long, state: Int): ApiResponse {
        return apiResponse(hotService.state(id, state))
    }

    /**
     *
     */
    @ApiOperation("删除推荐")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "id", value = "推荐id", paramType = "body", required = true, dataTypeClass = Long::class)
    ])
    @RequestAdmin
    @PostMapping("delete")
    fun delete(id: Long): ApiResponse {
        return apiResponse(hotService.delete(id))
    }

    /**
     *
     */
    @ApiOperation("推荐排序")
    @RequestAdmin
    @PostMapping("sort")
    fun sort(@RequestBody sort: List<SortBean>): ApiResponse {
        return apiResponse(hotService.sort(sort))
    }

}