package com.danilkha.contentreviews.api.users

import kotlinx.serialization.Serializable

@Serializable
enum class RoleApiModel {
    ADMIN,
    USER,
}