package com.github.luoyemyy.mall.common.cache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class AppCache {

    companion object {
        private const val TOKEN_CACHE = "tokenCache"
    }

    @Autowired
    private lateinit var cacheManager: CacheManager

    //*******************************************************************************
    //****************************** token 最多保存7天********************************
    //*******************************************************************************

    fun getUser(token: String?): CacheUser? {
        return token?.let {
            cacheManager.getCache(TOKEN_CACHE)?.let { cache ->
                cache.get(it, Long::class.java)?.let { userId ->
                    cache.get(userId, CacheUser::class.java)
                }
            }
        }
    }

    fun setUser(user: CacheUser) {
        user.userId?.also { userId ->
            if (userId > 0) {
                user.token?.also { token ->
                    if (token.isNotEmpty()) {
                        cacheManager.getCache(TOKEN_CACHE)?.also { cache ->
                            cache.put(token, userId)
                            cache.put(userId, user)
                        }
                    }
                }
            }
        }
    }
}