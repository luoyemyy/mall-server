package com.github.luoyemyy.mall.core.service.applet.bean

import com.github.luoyemyy.mall.core.entity.Address
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信收货地址")
open class AppletAddress {

    @ApiModelProperty("地址id", required = true)
    var id: Long = 0
    @ApiModelProperty("收件人", required = true)
    var name: String? = null
    @ApiModelProperty("收件人手机", required = true)
    var phone: String? = null
    @ApiModelProperty("邮编", required = true)
    var postCode: String? = null
    @ApiModelProperty("国家", required = true)
    var country: String? = null
    @ApiModelProperty("省", required = true)
    var province: String? = null
    @ApiModelProperty("市", required = true)
    var city: String? = null
    @ApiModelProperty("县", required = true)
    var county: String? = null
    @ApiModelProperty("街道", required = true)
    var street: String? = null
    @ApiModelProperty("详细地址", required = true)
    var summary: String? = null
    @ApiModelProperty("1 默认地址 0 其他地址", required = true)
    var type: Int = 0

    fun toAddress(): Address {
        return Address().also {
            it.name = name
            it.phone = phone
            it.postCode = postCode
            it.country = country
            it.province = province
            it.city = city
            it.county = county
            it.street = street
            it.summary = "$province $city $county $street"
        }
    }
}