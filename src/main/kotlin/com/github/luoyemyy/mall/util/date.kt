@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.luoyemyy.mall.util

import java.text.SimpleDateFormat

object DateUtil {
    const val ymd = "yyyy-MM-dd"
    const val ymdhms = "yyyy-MM-dd HH:mm:ss"
    val sdf_ymd = SimpleDateFormat(ymd)
    val sdf_ymdhms = SimpleDateFormat(ymdhms)
}