package com.danilkha.domain.model

import java.util.UUID

data class Account(
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