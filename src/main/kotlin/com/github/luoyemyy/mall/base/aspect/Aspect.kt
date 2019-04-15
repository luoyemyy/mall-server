@file:Suppress("unused")

package com.github.luoyemyy.mall.base.aspect

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.service.UserService
import com.github.luoyemyy.mall.core.service2.AppletUserService
import com.github.luoyemyy.mall.util.JsonUtil
import com.github.luoyemyy.mall.util.Role
import com.github.luoyemyy.mall.util.toJsonString
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
@Aspect
class Aspect {

    private val logger = LoggerFactory.getLogger("Aspect")

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var appletUserService: AppletUserService
    @Autowired
    private lateinit var request: HttpServletRequest

    private fun params(pjp: ProceedingJoinPoint): String? {
        val methodSignature = pjp.signature as? MethodSignature ?: return null
        val names = methodSignature.parameterNames ?: return null
        val values = pjp.args ?: return null
        if (names.isNotEmpty() && names.size == values.size) {
            return names.mapIndexed { index, s -> "$s=${print(values[index])}" }.joinToString("&")
        }
        return null
    }

    private fun print(any: Any?): String {
        return when (any) {
            is String -> any
            else -> any.toJsonString() ?: ""
        }
    }

    private fun preLog(pjp: ProceedingJoinPoint) {
        val path = request.servletPath
        val token = request.getHeader("token")
        val params = params(pjp) ?: ""
        logger.info("<<<<<<:method=[{}],path=[{}],token=[{}],params=[{}]", request.method, path, token, params)
    }

    private fun postLog(result: Any) {
        logger.info(">>>>>>:{}", JsonUtil.objectMapper.writeValueAsString(result))
    }

    /**
     * 拦截后台请求
     */
    @Around("execution(public * com.github.luoyemyy.mall.admin.*Controller.*(..)) && @annotation(flag)")
    fun around(pjp: ProceedingJoinPoint, flag: RequestAdmin): Any {
        preLog(pjp)
        if (flag.needLogin) {
            request.getHeader("token")?.apply {
                userService.getAdminUserByToken(this)?.apply {
                    if (Role.pass(role, flag.role)) {
                        request.setAttribute("roleId", role)
                        request.setAttribute("userId", userId)
                    } else throw MallException(Code.INVALID_ROLE)
                } ?: throw MallException(Code.INVALID_TOKEN)
            } ?: throw MallException(Code.INVALID_TOKEN)
        }
        return pjp.proceed().apply { postLog(this) }
    }

    /**
     * 拦截微信请求
     */
    @Around("execution(public * com.github.luoyemyy.mall.applet.*Controller.*(..)) && @annotation(flag)")
    fun around(pjp: ProceedingJoinPoint, flag: RequestApplet): Any {
        preLog(pjp)
        if (flag.needLogin) {
            request.getHeader("token")?.apply {
                appletUserService.getWeChatUserByToken(this)?.apply {
                    request.setAttribute("userId", userId)
                } ?: throw MallException(Code.INVALID_TOKEN)
            } ?: throw MallException(Code.INVALID_TOKEN)
        }
        return pjp.proceed().apply { postLog(this) }
    }
}