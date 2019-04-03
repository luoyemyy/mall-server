package com.github.luoyemyy.mall.util

object Role {

    const val SYSTEM_ID = 1
    const val ADMIN_ID = 2
    const val MANAGER_ID = 3

    private val SYSTEM = RoleInfo(SYSTEM_ID, "系统管理员")
    private val ADMIN = RoleInfo(ADMIN_ID, "管理员")
    private val MANAGER = RoleInfo(MANAGER_ID, "客服")

    fun pass(userRole: Int?, needRole: Int): Boolean {
        return userRole != null && userRole > 0 && userRole <= needRole
    }

    fun edit(userRole: Int?, editRole: Int): Boolean {
        return userRole != null && userRole > 0 && userRole < editRole
    }

    fun getName(roleId: Int?): String {
        return when (roleId) {
            SYSTEM_ID -> SYSTEM.name
            ADMIN_ID -> ADMIN.name
            else -> MANAGER.name
        }
    }

    fun getRoles(roleId: Int?): List<RoleInfo> {
        return when (roleId) {
            SYSTEM_ID -> listOf(ADMIN, MANAGER)
            ADMIN_ID -> listOf(MANAGER)
            else -> listOf()
        }
    }
}

class RoleInfo(val id: Int, var name: String)