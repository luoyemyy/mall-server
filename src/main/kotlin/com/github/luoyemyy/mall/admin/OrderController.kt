@file:Suppress("unused")

package com.github.luoyemyy.mall.admin

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.entity.Product
import com.github.luoyemyy.mall.core.service.OrderService
import com.github.luoyemyy.mall.core.service.ProductService
import com.github.luoyemyy.mall.util.Role
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-订单"])
@RestController
@RequestMapping("admin/order")
class OrderController : BaseController() {

    @Autowired
    private lateinit var orderService: OrderService

    /**
     *
     */
    @ApiOperation("产品列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "stateId", value = "状态id", required = true, dataTypeClass = Int::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])
    @RequestAdmin
    @GetMapping("list")
    fun list(stateId: Int, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<OrderItem> {
        return listResponse(orderService.list(stateId, page))
    }


}
