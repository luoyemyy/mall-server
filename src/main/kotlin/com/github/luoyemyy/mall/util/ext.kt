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


fun wxSign(obj: Any, mchKey: String?): String? {
    return (mutableListOf<String>().apply {
        val clazz = obj.javaClass
        clazz.fields.forEach {
            val name = it.name
            when (val value = it.get(clazz)) {
                null -> {
                    //pass
                }
                value is Int && value > 0 -> {
                    add("$name=$value")
                }
                else -> {
                    add("$name=$value")
                }
            }
        }
    }.sorted().joinToString("&") + "key=$mchKey").md5()?.toUpperCase()
}

fun wxSign2(map: MutableMap<String, Any?>, mchKey: String?): String? {
    map["key"] = mchKey
    return map.map {
        when (val value = it.value) {
            null -> ""
            value is Int && value == 0 -> ""
            else -> "${it.key}=${it.value}"
        }
    }.filter { it.isNotEmpty() }.sorted().joinToString("&").md5()?.toUpperCase()
}

fun newOrderNo(): String = UUID.randomUUID().toString().replace("-", "")
