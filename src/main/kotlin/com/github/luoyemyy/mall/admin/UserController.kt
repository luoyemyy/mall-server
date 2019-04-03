package com.github.luoyemyy.mall.admin

import com.github.luoyemyy.mall.base.BaseController
import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.aspect.RequestAdmin
import com.github.luoyemyy.mall.base.response.*
import com.github.luoyemyy.mall.core.bean.Manager
import com.github.luoyemyy.mall.core.service.UserService
import com.github.luoyemyy.mall.util.Role
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags = ["后台-管理员/客服"])
@RestController
@RequestMapping("admin/user")
class UserController : BaseController() {

    @Autowired
    private lateinit var userService: UserService

    /**
     *
     */
    @ApiOperation("客服列表（管理员可用）")
    @RequestAdmin(true, Role.ADMIN_ID)
    @ApiImplicitParam(name = "page", value = "页码", required = true)
    @GetMapping("list/manager")
    fun managerList(page: Int): ListResponse<Manager> {
        return listResponse(userService.getManagerByRoleAndPage(Role.MANAGER_ID, page))
    }

    /**
     *
     */
    @ApiOperation("管理员列表（系统管理员可用）")
    @RequestAdmin(true, Role.SYSTEM_ID)
    @ApiImplicitParam(name = "page", value = "页码", required = true)
    @GetMapping("list/admin")
    fun adminList(page: Int): ListResponse<Manager> {
        return listResponse(userService.getManagerByRoleAndPage(Role.ADMIN_ID, page))
    }

    /**
     *
     */
    @ApiOperation("新增管理员/客服（管理员可用）")
    @RequestAdmin(true, Role.ADMIN_ID)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "phone", value = "手机号", required = true),
        ApiImplicitParam(name = "password", value = "密码", required = true),
        ApiImplicitParam(name = "roleId", value = "角色", required = true)])
    @PostMapping("add")
    fun add(phone: String, password: String, roleId: Int): AlertResponse {
        if (!Role.edit(roleId(), roleId)) throw MallException(Code.INVALID_ROLE)
        return alertResponse(userService.add(phone, password, roleId))
    }

    /**
     *
     */
    @ApiOperation("编辑权限（系统管理员可用）")
    @RequestAdmin(true, Role.SYSTEM_ID)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "userId", value = "用户Id", required = true),
        ApiImplicitParam(name = "roleId", value = "角色", required = true)])
    @PostMapping("editRole")
    fun editRole(userId: Long, roleId: Int): ApiResponse {
        return apiResponse(userService.editRole(userId, roleId))
    }

    /**
     *
     */
    @ApiOperation("管理员/客服是否有效（管理员可用）")
    @RequestAdmin(true, Role.ADMIN_ID)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "userId", value = "用户Id", required = true),
        ApiImplicitParam(name = "state", value = "是否有效 1 有效 0 无效", required = true)])
    @PostMapping("editState")
    fun editState(userId: Long, state: Int): ApiResponse {
        return apiResponse(userService.editState(userId, state))
    }

    /**
     *
     */
    @ApiOperation("删除管理员/客服（管理员可用）")
    @RequestAdmin(true, Role.ADMIN_ID)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "userId", value = "用户Id", required = true)])
    @PostMapping("delete")
    fun delete(userId: Long): ApiResponse {
        return apiResponse(userService.delete(roleId(), userId))
    }

    /**
     *
     */
    @ApiOperation("管理员重置客服的密码（系统管理员可用）")
    @RequestAdmin(true, Role.SYSTEM_ID)
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "userId", value = "用户Id", required = true),
        ApiImplicitParam(name = "newPassword", value = "密码", required = true)])
    @PostMapping("passwordByAdmin")
    fun resetPasswordByAdmin(userId: Long, newPassword: String): ApiResponse {
        return apiResponse(userService.resetPasswordBySystem(userId, newPassword))
    }

    /**
     *
     */
    @ApiOperation("登录用户重置密码")
    @RequestAdmin
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true),
        ApiImplicitParam(name = "newPassword", value = "新密码", required = true)])
    @PostMapping("passwordBySelf")
    fun resetPasswordBySelf(oldPassword: String, newPassword: String): ApiResponse {
        return apiResponse(userService.resetPasswordBySelf(userId(), oldPassword, newPassword))
    }

    /**
     *
     */
    @ApiOperation("登录用户修改用户信息")
    @RequestAdmin
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "name", value = "名称", required = false),
        ApiImplicitParam(name = "gender", value = "性别，不修改传-1", required = false),
        ApiImplicitParam(name = "image", value = "头像", required = false)])
    @PostMapping("editInfo")
    fun editInfo(@RequestParam(required = false) name: String?,
                 @RequestParam(required = false) gender: Int,
                 @RequestParam(required = false) image: String?): ApiResponse {
        return apiResponse(userService.editInfo(userId(), name, gender, image))
    }


}