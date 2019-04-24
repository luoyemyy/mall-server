package com.github.luoyemyy.mall.util

import java.util.*
import kotlin.random.Random

fun Int.toPageStart(): Int = (this - 1) * 10

fun newRandomString(length: Int): String {
    val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    val sb = StringBuffer()
    for (i in 0 until length) {
        val number = Random.nextInt(62)
        sb.append(str[number])
    }
    return sb.toString()
}


fun wxRequestSign(map: MutableMap<String, Any?>, mchKey: String?): String? {
    map["key"] = mchKey
    return map.map {
        when (val value = it.value) {
            null -> ""
            value is Int && value == 0 -> ""
            else -> "${it.key}=${it.value}"
        }
    }.filter { it.isNotEmpty() }.sorted().joinToString("&").md5()?.toUpperCase()
}

fun wxResponseSign(map: MutableMap<String, Any?>, mchKey: String?): String? {
    map.remove("sign")
    return wxRequestSign(map, mchKey)
}

fun newOrderNo(): String = UUID.randomUUID().toString().replace("-", "")
