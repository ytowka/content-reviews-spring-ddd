package com.danilkha.contentreviews.api.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoadFileResult(
    val imageId: String
)