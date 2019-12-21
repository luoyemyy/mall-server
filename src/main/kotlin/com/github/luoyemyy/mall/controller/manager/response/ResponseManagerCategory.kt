package com.github.luoyemyy.mall.controller.manager.response

import com.github.luoyemyy.mall.core.entity.Category
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("分类")
class ResponseManagerCategory {
    @ApiModelProperty("分类Id")
    var id: Long = 0
    @ApiModelProperty("分类名称")
    var name: String? = null
    @ApiModelProperty("排序号")
    var sort: Int = 0
    @ApiModelProperty("是否有效 1 有效 0 无效")
    var state: Int = 0

    companion object {
        fun map(category: Category): ResponseManagerCategory {
            return ResponseManagerCategory().apply {
                id = category.id
                name = category.name
                sort = category.sort
                state = category.state
            }
        }
    }
}