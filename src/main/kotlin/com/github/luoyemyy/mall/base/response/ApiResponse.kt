package com.github.luoyemyy.mall.base.response

import com.github.luoyemyy.mall.base.advice.Code
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("请求结果")
open class ApiResponse {
    @ApiModelProperty("0 成功； 其他 失败 ")
    var code: Int = Code.SUCCESS
    @ApiModelProperty("描述")
    var msg: String? = Code.msg(Code.SUCCESS)
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
            this.msg = it.second
        }
    }
}

fun apiResponse(code: Int = Code.SUCCESS, msg: String = Code.msg(code)): ApiResponse {
    return ApiResponse().apply {
        this.code = code
        this.msg = msg
    }
}

fun apiResponse(success: Boolean): ApiResponse {
    return ApiResponse().apply {
        this.code = if (success) Code.SUCCESS else Code.FAILURE
        this.msg = Code.msg(code)
    }
}

