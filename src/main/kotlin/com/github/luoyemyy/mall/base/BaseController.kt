package com.github.luoyemyy.mall.base

import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest

open class BaseController {

    @Autowired
    private lateinit var request: HttpServletRequest

    /**
     * @admin
     * @applet
     */
    protected fun userId(): Long {
        return request.getAttribute("userId") as? Long ?: 0L
    }

    /**
     * @admin
     */
    protected fun roleId(): Int {
        return request.getAttribute("roleId") as? Int ?: 0
    }
}