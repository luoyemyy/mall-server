@file:Suppress("unused")

package com.github.luoyemyy.mall.controller.manager

import com.github.luoyemyy.mall.common.aspect.AppApi
import com.github.luoyemyy.mall.controller.base.BaseController
import com.github.luoyemyy.mall.controller.response.DataResponse
import com.github.luoyemyy.mall.controller.response.ListResponse
import com.github.luoyemyy.mall.controller.response.dataResponse
import com.github.luoyemyy.mall.controller.response.listResponse
import com.github.luoyemyy.mall.core.service.admin.OrderService
import com.github.luoyemyy.mall.core.service.admin.bean.OrderDetail
import com.github.luoyemyy.mall.core.service.admin.bean.OrderItem
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-订单"])
@RestController
@RequestMapping("admin/order")
class ManagerOrderController04 : BaseController() {

    @Autowired
    private lateinit var orderService: OrderService

    @AppApi(pathId = 10401)
    @ApiOperation("订单列表")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "stateId", value = "状态id", required = true, dataTypeClass = Int::class),
        ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataTypeClass = Int::class)])
   
    @GetMapping("list")
    fun list(stateId: Int, @RequestParam(required = false, defaultValue = "1") page: Int): ListResponse<OrderItem> {
        return listResponse(orderService.list(stateId, page))
    }

    @AppApi(pathId = 10402)
    @ApiOperation("订单详情")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class)
    ])
   
    @GetMapping("detail")
    fun detail(orderId: Long): DataResponse<OrderDetail> {
        return dataResponse(orderService.detail(orderId))
    }

    /**
     * 0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成
     * 6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消
     */
    @AppApi(pathId = 10403)
    @ApiOperation("已支付，待确认-查询订单支付结果")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataTypeClass = Long::class),
        ApiImplicitParam(name = "expressCompany", value = "快递公司state=3必须", required = false, dataTypeClass = String::class),
        ApiImplicitParam(name = "expressNo", value = "快递单号state=3必须", required = true, dataTypeClass = String::class),
        ApiImplicitParam(name = "refundMoney", value = "退款金额state=6,8必须", required = true, dataTypeClass = Float::class)
    ])
   
    @PostMapping("state")
    fun state(orderId: Long,
              @RequestParam(name = "expressCompany", required = false) expressCompany: String? = null,
              @RequestParam(name = "expressNo", required = false) expressNo: String? = null,
              @RequestParam(name = "refundMoney", required = false) refundMoney: Float = 0f): DataResponse<Boolean> {
        return dataResponse(orderService.state(orderId, expressCompany, expressNo,refundMoney))
    }

}
