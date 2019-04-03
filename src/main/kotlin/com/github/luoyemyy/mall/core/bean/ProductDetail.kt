package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("产品详情")
class ProductDetail : ProductBean() {

    /**
     * 自定义
     */
    @ApiModelProperty("产品滑动展示图片",required = true)
    var swipeImages: List<ProductImageBean>? = null

    @ApiModelProperty("产品描述图片",required = true)
    var descImages: List<ProductImageBean>? = null

    @ApiModelProperty("分类id",required = true)
    var categoryIds: List<Long>? = null

}