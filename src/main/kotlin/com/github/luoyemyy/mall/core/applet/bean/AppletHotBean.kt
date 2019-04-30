package com.github.luoyemyy.mall.core.applet.bean

import com.github.luoyemyy.mall.core.entity.Hot
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信推荐")
class AppletHotBean {
    @ApiModelProperty("推荐Id")
    var id: Long = 0
    @ApiModelProperty("封面地址")
    var image: String? = null
    @ApiModelProperty("描述")
    var description: String? = null
    @ApiModelProperty("产品id列表")
    var productIds: List<Long>? = null

    companion object {
        fun map(hot: Hot, ids: List<Long>?): AppletHotBean {
            return AppletHotBean().apply {
                id = hot.id
                image = hot.image
                description = hot.description
                productIds = ids
            }
        }
    }
}