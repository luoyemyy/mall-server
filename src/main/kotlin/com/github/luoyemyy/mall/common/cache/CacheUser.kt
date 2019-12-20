package com.github.luoyemyy.mall.common.cache

class CacheUser(var token: String?,
                var userId: Long?) {

    companion object {
        private val threadLocal = ThreadLocal<CacheUser>()

        fun getUser(): CacheUser {
            return threadLocal.get()
        }

        /**
         * 登录成功后和请求拦截前需要将user写入当前线程对象中
         *
         */
        fun setUser(user: CacheUser) {
            threadLocal.set(user)
        }
    }
}