package com.danilkha.contentreviews.api.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ReviewResponse (
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