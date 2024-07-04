package com.danilkha.contentreviews.api.users

import kotlinx.serialization.Serializable

@Serializable
data class UserTasteMatchScoreResponse(
    val userId: String,
    val score: Float
)