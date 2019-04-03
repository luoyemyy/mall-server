package com.github.luoyemyy.mall.core.bean

import com.github.luoyemyy.mall.core.entity.ProductImage
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("产品图片")
open class ProductImageBean {
    @ApiModelProperty("主键，新增时为0", required = true)
    var id: Long = 0
    @ApiModelProperty("图片地址", required = true)
    var image: String? = null

    companion object {
        fun fromProductImage(productImage: ProductImage): ProductImageBean {
            return ProductImageBean().apply {
                id = productImage.id
                image = productImage.image
            }
        }
    }
}