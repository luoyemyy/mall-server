package com.github.luoyemyy.mall.core.service.admin

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.common.properties.AliOss
import com.github.luoyemyy.mall.core.service.admin.bean.LoginUser
import com.github.luoyemyy.mall.core.service.admin.bean.Manager
import com.github.luoyemyy.mall.core.dao.UserDao
import com.github.luoyemyy.mall.core.entity.AdminUser
import com.github.luoyemyy.mall.core.entity.AdminUserExample
import com.github.luoyemyy.mall.core.entity.User
import com.github.luoyemyy.mall.core.entity.UserExample
import com.github.luoyemyy.mall.core.mapper.AdminUserMapper
import com.github.luoyemyy.mall.core.mapper.UserMapper
import com.github.luoyemyy.mall.util.Role
import com.github.luoyemyy.mall.util.md5
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper
    @Autowired
    private lateinit var userDao: UserDao
    @Autowired
    private lateinit var adminUserMapper: AdminUserMapper
    @Autowired
    private lateinit var aliOss: AliOss

    /**
     * @admin
     */
    fun getAdminUserByToken(token: String): AdminUser? {
        return adminUserMapper.selectByExample(AdminUserExample().apply { createCriteria().andTokenEqualTo(token).andStateEqualTo(1) })?.firstOrNull()
    }

    /**
     * @admin
     */
    private fun getAdminUserByUser(userId: Long): AdminUser? {
        return adminUserMapper.selectByExample(AdminUserExample().apply { createCriteria().andUserIdEqualTo(userId).andStateEqualTo(1) })?.firstOrNull()
    }

    /**
     * @admin
     */
    private fun getUserByPhone(phone: String): User? {
        return userMapper.selectByExample(UserExample().apply { createCriteria().andPhoneEqualTo(phone) }).firstOrNull()
    }

    /**
     * @admin
     */
    @Transactional
    fun adminLogin(phone: String, password: String): LoginUser {
        val user = getUserByPhone(phone) ?: throw AppException(AppCode.LOGIN_FAILURE)
        if (!user.password.equals(password, true)) {
            throw AppException(AppCode.LOGIN_FAILURE)
        }
        val admin = getAdminUserByUser(user.id) ?: throw AppException(AppCode.INVALID_ADMIN)
        admin.token = System.currentTimeMillis().toString().md5()
        if (adminUserMapper.updateByPrimaryKeySelective(admin) == 0) throw AppException(AppCode.FAIL)

        return LoginUser.fromUser(user, admin.token, admin.role).apply {
            ossAk = aliOss.accessKey
            ossSk = aliOss.secretKey
            ossEp = aliOss.endPoint
            ossBucket = aliOss.bucket
        }
    }

    /**
     * @admin
     */
    fun getManagerByRoleAndPage(roleId: Int, page: Int): List<Manager> {
        return userDao.selectByPage(roleId, page.toPageStart())?.apply {
            forEach {
                it.roleName = Role.getName(it.roleId)
            }
        } ?: listOf()
    }

    /**
     * @admin
     */
    @Transactional
    fun add(phone: String, password: String, roleId: Int): String? {
        getUserByPhone(phone)?.apply {
            getAdminUserByUser(id)?.apply {
                throw AppException(AppCode.EXIST_MANAGER)
            }
            AdminUser().also {
                it.userId = id
                it.role = roleId
                it.state = 1
                adminUserMapper.insert(it)
            }
            val roleName = Role.getName(roleId)
            return "该手机号已使用，但还不是$roleName，已经将此手机号关联为$roleName，登录密码本次未修改"
        }
        User().also {
            it.phone = phone
            it.password = password
            it.name = "_$phone"
            it.gender = 0
            it.createTime = Date()
            it.updateTime = it.createTime
            if (userMapper.insert(it) > 0) {
                AdminUser().also { a ->
                    a.userId = it.id
                    a.role = roleId
                    a.state = 1
                    adminUserMapper.insert(a)
                }
                return null
            }
            throw AppException(AppCode.FAIL)
        }
    }

    /**
     * @admin
     */
    @Transactional
    fun editRole(userId: Long, roleId: Int): Boolean {
        return adminUserMapper.updateByExampleSelective(AdminUser().also { it.role = roleId }, AdminUserExample().also { it.createCriteria().andUserIdEqualTo(userId) }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun editState(userId: Long, state: Int): Boolean {
        return adminUserMapper.updateByExampleSelective(AdminUser().also { it.state = state }, AdminUserExample().also { it.createCriteria().andUserIdEqualTo(userId) }) > 0
    }

    /**
     * 当前登录的管理人员只能删除下一级的管理人员
     * @admin
     */
    @Transactional
    fun delete(loginRoleId: Int, userId: Long): Boolean {
        return adminUserMapper.deleteByExample(AdminUserExample().apply {
            createCriteria().andUserIdEqualTo(userId).andRoleGreaterThan(loginRoleId)
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun resetPasswordBySystem(userId: Long, newPassword: String): Boolean {
        return userMapper.updateByPrimaryKeySelective(User().apply {
            id = userId
            password = newPassword
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun resetPasswordBySelf(loginUserId: Long, oldPassword: String, newPassword: String): Boolean {
        return userMapper.selectByPrimaryKey(loginUserId)?.let {
            if (!oldPassword.equals(it.password, true)) {
                throw AppException(AppCode.OLD_PASSWORD_ERROR)
            } else {
                userMapper.updateByPrimaryKeySelective(
                        User().apply {
                            id = loginUserId
                            password = newPassword
                        }) > 0
            }
        } ?: false
    }

    /**
     * @admin
     */
    @Transactional
    fun editInfo(loginUserId: Long, name: String?, gender: Int, image: String?): Boolean {
        val user = User()
        user.id = loginUserId
        var update = false
        if (!name.isNullOrEmpty()) {
            user.name = name
            update = true
        }
        if (gender >= 0) {
            user.gender = gender
            update = true
        }
        if (!image.isNullOrEmpty()) {
            user.headImage = image
            update = true
        }
        if (update) {
            userMapper.updateByPrimaryKeySelective(user)
        }
        return true
    }
}