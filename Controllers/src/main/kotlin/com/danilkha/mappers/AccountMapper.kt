package com.danilkha.mappers

import com.danilkha.contentreviews.api.users.RoleApiModel
import com.danilkha.contentreviews.api.users.UserResponse
import com.danilkha.controller.FILES_PATH
import com.danilkha.domain.model.Account
import com.danilkha.domain.model.Role


fun Account.toResponse(): UserResponse = UserResponse(
    id = this.id.toString(),
    fullName = this.fullName,
    login = this.login,
    email = this.email,
    phone = this.phone,
    role = this.role.toApiModel(),
    isBlocked = isBlocked,
    avatarUrl = avatarFileName?.let { "$FILES_PATH/$avatarFileName" },
    activated = activated
)


fun Role.toApiModel(): RoleApiModel = when (this) {
    Role.ADMIN -> RoleApiModel.ADMIN
    Role.USER -> RoleApiModel.USER
}

fun RoleApiModel.toRole(): Role = when (this) {
    RoleApiModel.USER -> Role.USER
    RoleApiModel.ADMIN -> Role.ADMIN
}