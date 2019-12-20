package com.github.luoyemyy.mall.core.service.admin.bean

import com.github.luoyemyy.mall.util.toJsonString
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("排序结果")
class SortBean {
    @ApiModelProperty("主键id", required = true)
    var id: Long = 0
    @ApiModelProperty("排序号", required = true)
    var sort: Int = 0
    @ApiModelProperty("其他id", required = true)
    var typeId: Long = 0

    override fun toString(): String {
        return toJsonString() ?: "{}"
    }
}