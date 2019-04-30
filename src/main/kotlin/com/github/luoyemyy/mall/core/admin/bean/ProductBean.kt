package com.github.luoyemyy.mall.core.admin.bean

import com.github.luoyemyy.mall.core.entity.Product
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("产品")
open class ProductBean {
    /**
     * product
     */
    @ApiModelProperty("产品id，新增产品时为0", required = true)
    var id: Long = 0
    @ApiModelProperty("产品封面", required = true)
    var coverImage: String? = null
    @ApiModelProperty("产品名称", required = true)
    var name: String? = null
    @ApiModelProperty("产品描述", required = true)
    var description: String? = null
    @ApiModelProperty("市场价格", required = true)
    var marketPrice: Float = 0f
    @ApiModelProperty("实际价格", required = true)
    var actualPrice: Float = 0f
    @ApiModelProperty("库存", required = true)
    var stock: Int = 0
    @ApiModelProperty("是否上架", required = true)
    var online: Boolean = false
    @ApiModelProperty("排序号", required = true)
    var sort: Int = 0

    companion object {

        fun fromProduct(product: Product): ProductBean {
            return ProductBean().apply {
                id = product.id
                coverImage = product.coverImage
                name = product.name
                description = product.description
                marketPrice = product.marketPrice
                actualPrice = product.actualPrice
                stock = product.stock
                online = product.online
                sort = product.sort
            }
        }
    }
}