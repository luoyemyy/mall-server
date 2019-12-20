package com.github.luoyemyy.mall.core.service.admin.bean

import com.github.luoyemyy.mall.core.entity.ProductImage
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("产品图片")
open class ProductImageBean {
    @ApiModelProperty("主键，新增时为0", required = true)
    var id: Long = 0
    @ApiModelProperty("图片地址", required = true)
    var image: String? = null
    @ApiModelProperty("类型 1 轮播 2 描述", required = true)
    var type: Int = 0

    companion object {
        fun fromProductImage(productImage: ProductImage): ProductImageBean {
            return ProductImageBean().apply {
                id = productImage.id
                image = productImage.image
                type = productImage.type
            }
        }
    }
}