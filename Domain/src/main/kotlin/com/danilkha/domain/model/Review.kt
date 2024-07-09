package com.danilkha.domain.model

import java.sql.Timestamp
import java.util.UUID

class Review(
    val id: Long,
    val userId: UUID,
    val contentId: Long,
    val contentName: String,
    val userAvatarUrl: String?,
    val userName: String,
    val mark: Int,
    val writeTime: Timestamp,
    val text: String
)