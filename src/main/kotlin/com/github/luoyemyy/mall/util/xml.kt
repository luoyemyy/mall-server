package com.github.luoyemyy.mall.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.util.*
import kotlin.collections.HashMap

inline fun <reified T> String?.xmlToList(): List<T>? {
    val type = JsonUtil.objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

inline fun <reified T> String?.xmlToLinkedList(): List<T>? {
    val type = JsonUtil.objectMapper.typeFactory.constructCollectionType(LinkedList::class.java, T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

inline fun <reified R, reified T> String?.xmlToMap(): Map<R, T>? {
    val type = JsonUtil.objectMapper.typeFactory.constructMapType(HashMap::class.java, R::class.java, T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

inline fun <reified T> String?.xmlToObject(): T? {
    val type = JsonUtil.objectMapper.typeFactory.constructType(T::class.java)
    return if (this == null) null else JsonUtil.objectMapper.readValue(this, type)
}

fun Any.toXmlString(): String {
    return JsonUtil.objectMapper.writeValueAsString(this)
}

object XmlUtil {
    val objectMapper = XmlMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        dateFormat = DateUtil.sdf_ymdhms
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
}