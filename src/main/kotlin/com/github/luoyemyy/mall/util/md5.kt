package com.github.luoyemyy.mall.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String?.md5(): String? {

    if (this == null || this.isEmpty())
        return null
    try {
        val messageDigest = MessageDigest.getInstance("md5")
        messageDigest.update(this.toByteArray())
        val bytes = messageDigest.digest()
        val stringBuffer = StringBuilder(2 * bytes.size)
        bytes.forEach {
            val x = it.toInt() and 0xff
            if (x <= 0xf) {
                stringBuffer.append(0)
            }
            stringBuffer.append(Integer.toHexString(x))
        }
        return stringBuffer.toString().toUpperCase()
    } catch (e: NoSuchAlgorithmException) {
        println(e)
    }
    return null
}