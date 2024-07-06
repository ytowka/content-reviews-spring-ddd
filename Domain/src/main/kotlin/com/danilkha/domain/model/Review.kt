package com.danilkha.domain.model

class Review(
    val id: Long,
    val userId: String,
    val contentId: Long,
    val contentName: String,
    val userAvatarUrl: String?,
    val userName: String,
    val mark: Int,
    val writeTime: Long,
    val text: String
)