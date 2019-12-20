package com.github.luoyemyy.mall.util

import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

fun String?.md5(): String? {
    return if (this.isNullOrEmpty()){
        this
    }else{
        DigestUtils.md5Hex(this)
    }
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