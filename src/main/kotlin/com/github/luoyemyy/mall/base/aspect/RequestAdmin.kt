package com.github.luoyemyy.mall.base.aspect

import com.github.luoyemyy.mall.util.Role

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class RequestAdmin(val needLogin: Boolean = true, val role: Int = Role.MANAGER_ID)