package com.github.luoyemyy.mall.core.bean

import com.github.luoyemyy.mall.core.entity.ProductImage
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("产品模板图片")
open class ProductTemplateImage {
    @ApiModelProperty("主键，新增时为0", required = true)
    var id: Long = 0
    @ApiModelProperty("图片地址", required = true)
    var image: String? = null
    @ApiModelProperty("排序号", required = true)
    var sort: Int = 0
    @ApiModelProperty("类型 3 轮播 4 描述", required = true)
    var type: Int = 0

    companion object {
        fun fromProductImage(productImage: ProductImage): ProductTemplateImage {
            return ProductTemplateImage().apply {
                id = productImage.id
                image = productImage.image
                sort = productImage.sort
                type = productImage.type
            }
        }
    }
}