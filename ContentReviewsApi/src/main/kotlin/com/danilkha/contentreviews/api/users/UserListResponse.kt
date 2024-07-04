package com.danilkha.contentreviews.api.users

import kotlinx.serialization.Serializable

@Serializable
data class UserListResponse(
    val list: List<UserResponse>,
    val page: Int,
    val hasNextPage: Boolean,
)