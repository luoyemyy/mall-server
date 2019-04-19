package com.github.luoyemyy.mall.core.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("微信订单数量统计")
class AppletOrderIndex {
    @ApiModelProperty("待支付数量")
    var pay: Int = 0
    @ApiModelProperty("待收货数量")
    var accept: Int = 0
    @ApiModelProperty("待退货数量")
    var refuse: Int = 0
}