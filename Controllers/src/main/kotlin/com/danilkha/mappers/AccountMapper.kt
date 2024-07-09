package com.danilkha.mappers

import com.danilkha.contentreviews.api.users.RoleApiModel
import com.danilkha.contentreviews.api.users.UserListResponse
import com.danilkha.contentreviews.api.users.UserResponse
import com.danilkha.controller.FILES_PATH
import com.danilkha.domain.model.Account
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Role


fun Account.toResponse(): UserResponse = UserResponse(
    id = this.id.toString(),
    fullName = this.fullName,
    login = this.login,
    email = this.email,
    phone = this.phone,
    role = this.role.toApiModel(),
    isBlocked = isBlocked,
    avatarUrl = avatarFileName?.asFullPathUrl(),
    activated = activated
)


fun PagedData<Account>.toResponse(): UserListResponse = UserListResponse(
    list = data.map { it.toResponse() },
    page = page,
    hasNextPage = hasNextPage,
)


fun Role.toApiModel(): RoleApiModel = when (this) {
    Role.ADMIN -> RoleApiModel.ADMIN
    Role.USER -> RoleApiModel.USER
}

fun RoleApiModel.toRole(): Role = when (this) {
    RoleApiModel.USER -> Role.USER
    RoleApiModel.ADMIN -> Role.ADMIN
}

fun String?.asFullPathUrl(): String? = this?.let { "$FILES_PATH/$it" }