package com.danilkha.contentreviews.api.content

import kotlinx.serialization.Serializable
import java.awt.Image

@Serializable
data class ContentResponse(
    val id: Long,
    val themeId: Long,
    val name: String,
    val imageUrl: String?,
    val avgMark: Float?,
    val reviewCount: Int,
)