package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("邮费")
class PostageBean {
    @ApiModelProperty("邮费id")
    var id: Long = 0
    @ApiModelProperty("邮费区域")
    var area: String? = null
    @ApiModelProperty("邮费价格")
    var price: Float = 0f
    @ApiModelProperty("是否邮寄 0 不邮寄 1邮寄")
    var post: Int = 0
}