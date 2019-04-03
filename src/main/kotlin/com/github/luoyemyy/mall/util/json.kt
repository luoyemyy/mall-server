package com.github.luoyemyy.mall.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

inline fun <reified T> String?.toList(): List<T>? {
    val type = JsonUtil.objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

inline fun <reified T> String?.toLinkedList(): List<T>? {
    val type = JsonUtil.objectMapper.typeFactory.constructCollectionType(LinkedList::class.java, T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

inline fun <reified T> String?.toObject(): T? {
    val type = JsonUtil.objectMapper.typeFactory.constructType(T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

fun Any?.toJsonString(): String? {
    return if (this == null) null else JsonUtil.objectMapper.writeValueAsString(this)
}

object JsonUtil {
    val objectMapper = ObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        dateFormat = DateUtil.sdf_ymdhms
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
}