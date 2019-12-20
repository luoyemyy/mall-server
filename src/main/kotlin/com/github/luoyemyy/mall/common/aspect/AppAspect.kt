package com.github.luoyemyy.mall.common.aspect

import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.common.advice.LogWriter
import com.github.luoyemyy.mall.common.advice.authException
import com.github.luoyemyy.mall.common.cache.AppCache
import com.github.luoyemyy.mall.common.cache.CacheUser
import com.github.luoyemyy.mall.core.service.admin.UserService
import com.github.luoyemyy.mall.core.service.applet.AppletUserService
import com.github.luoyemyy.mall.util.Const
import com.github.luoyemyy.mall.util.toJsonString
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest

@Aspect
@Component
class AppAspect {
    @Autowired
    private lateinit var request: HttpServletRequest
    @Autowired
    private lateinit var appCache: AppCache
    @Autowired
    private lateinit var logWriter: LogWriter
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var appletUserService: AppletUserService

    @Around("within(com.github.luoyemyy.mall.controller..*)" +
            "&& @annotation(appApi) ")
    @Throws(Throwable::class)
    fun aspect(pjp: ProceedingJoinPoint, appApi: AppApi): Any? {
        val path = request.servletPath
        val token = request.getHeader(Const.TOKEN_KEY)
        val params = params(pjp)
        logWriter.preLog(request.method, appApi.pathId, path, token, params)
        auth(parseGroup(appApi.pathId), token, appApi)
        val result = wrapper(pjp.proceed())
        val response = print(result)
        logWriter.postLog(response)
        return result
    }

    /**
     * @param group 1 manager 2 applet
     */
    private fun auth(group: Int, token: String, appApi: AppApi) {
        if (appApi.auth) {
            if (!StringUtils.isEmpty(token)) {
                val user = appCache.getUser(token)
                if (user != null) {
                    CacheUser.setUser(user)
                    return
                }
            }
            throw authException()
        }
    }

    private fun params(pjp: ProceedingJoinPoint): String? {
        val methodSignature = pjp.signature as? MethodSignature ?: return null
        val names = methodSignature.parameterNames ?: return null
        val values = pjp.args ?: return null
        if (names.isNotEmpty() && names.size == values.size) {
            return names.mapIndexed { index, s -> "$s=${print(values[index])}" }.joinToString("&")
        }
        return null
    }

    private fun wrapper(result: Any): Any? {
        return result as? String ?: result as? ApiResponse ?: apiResponse()
    }

    private fun parseGroup(pathId: Int): Int {
        return pathId / 10000
    }

    private fun print(obj: Any?): String? {
        return when (obj) {
            null -> null
            is String -> obj
            is Number -> obj.toString()
            else -> obj.toJsonString()
        }
    }
}