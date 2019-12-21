package com.github.luoyemyy.mall.common.aspect

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class AppApi(
        val pathId: Int,                // manager 10000+; applet 20000+;
        val pathName: String = "",
        val groupId: Int = 0,
        val groupName: String = "",
        val auth: Boolean = true)