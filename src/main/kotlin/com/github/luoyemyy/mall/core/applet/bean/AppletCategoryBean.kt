package com.github.luoyemyy.mall.core.applet.bean

import com.github.luoyemyy.mall.core.entity.Category
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信分类")
class AppletCategoryBean {
    @ApiModelProperty("分类Id")
    var id: Long = 0
    @ApiModelProperty("分类名称")
    var name: String? = null

    companion object {
        fun map(category: Category): AppletCategoryBean {
            return AppletCategoryBean().apply {
                id = category.id
                name = category.name
            }
        }
    }
}