package com.github.luoyemyy.mall.core.service.admin.bean

import com.github.luoyemyy.mall.core.entity.Hot
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("推荐")
class HotBean {
    @ApiModelProperty("推荐Id")
    var id: Long = 0
    @ApiModelProperty("封面地址")
    var image: String? = null
    @ApiModelProperty("描述")
    var description: String? = null
    @ApiModelProperty("排序号")
    var sort: Int = 0
    @ApiModelProperty("有效 0无效 1有效")
    var state: Int = 0
    @ApiModelProperty("产品数量")
    var count: Int = 0

    companion object {
        fun mapHot(hot: Hot, countProduct: Int): HotBean {
            return HotBean().apply {
                id = hot.id
                image = hot.image
                description = hot.description
                sort = hot.sort ?: 0
                state = hot.state ?: 0
                count = countProduct
            }
        }
    }
}