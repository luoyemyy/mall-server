package com.github.luoyemyy.mall.controller.response

import com.github.luoyemyy.mall.common.advice.AppCode
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("请求结果")
open class ApiResponse {
    @ApiModelProperty("0 成功； 其他 失败 ")
    var code: Int = AppCode.OK
    @ApiModelProperty("错误码描述")
    var codeMsg: String? = AppCode.msg(AppCode.OK)
    @ApiModelProperty("业务描述")
    var errorMsg: String? = null
}

class DataResponse<T>(@ApiModelProperty("返回对象") var data: T? = null) : ApiResponse()

class ListResponse<T>(@ApiModelProperty("返回列表") var list: List<T>? = null) : ApiResponse()

class IdResponse(@ApiModelProperty("返回主键") var id: Long = 0) : ApiResponse()

class AlertResponse(@ApiModelProperty("返回需提醒的信息") var alert: String? = null) : ApiResponse()

fun <T> dataResponse(data: T?): DataResponse<T> {
    return DataResponse(data)
}

fun <T> listResponse(list: List<T>?): ListResponse<T> {
    return ListResponse(list)
}

fun idResponse(id: Long): IdResponse {
    return IdResponse(id)
}

fun alertResponse(alert: String?): AlertResponse {
    return AlertResponse(alert)
}

fun apiResponse(vararg values: Pair<Int, String?>): ApiResponse {
    return apiResponse().apply {
        values.forEach {
            this.code = it.first
            this.codeMsg = it.second
        }
    }
}

fun apiResponse(code: Int = AppCode.OK, msg: String = AppCode.msg(code)): ApiResponse {
    return ApiResponse().apply {
        this.code = code
        this.codeMsg = msg
    }
}

fun apiResponse(success: Boolean): ApiResponse {
    return ApiResponse().apply {
        this.code = if (success) AppCode.OK else AppCode.FAIL
        this.codeMsg = AppCode.msg(code)
    }
}

