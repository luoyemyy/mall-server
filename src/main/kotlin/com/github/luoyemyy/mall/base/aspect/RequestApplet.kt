package com.github.luoyemyy.mall.base.aspect

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class RequestApplet(val needLogin: Boolean = true)