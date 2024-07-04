package com.danilkha.contentreviewsbackend.model

import com.danilkha.contentreviews.api.users.UserResponse
import com.danilkha.contentreviewsbackend.controller.FILES_PATH
import java.util.UUID

data class AccountDto(
    val id: UUID,
    val fullName: String,
    val login: String,
    val email: String,
    val avatarFileName: String?,
    val phone: String,
    val password: String,
    val role: Role,
    val isBlocked: Boolean,
    val activated: Boolean,
)

fun AccountDto.toResponse(): UserResponse = UserResponse(
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
