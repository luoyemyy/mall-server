package com.github.luoyemyy.mall.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

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


fun String.aesDecode(key: String): String {
    val secretKey = KeyGenerator.getInstance("AES").let {
        it.init(256, SecureRandom(key.toByteArray()))
        SecretKeySpec(it.generateKey().encoded, "AES")
    }
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding").apply {
        init(Cipher.ENCRYPT_MODE, secretKey)
    }
    val ba = cipher.doFinal(Base64.getDecoder().decode(this.toByteArray()))
    return String(Base64.getEncoder().encode(ba))
}