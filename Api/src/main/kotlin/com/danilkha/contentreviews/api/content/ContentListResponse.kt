package com.danilkha.contentreviews.api.content

import kotlinx.serialization.Serializable

@Serializable
data class ContentListResponse(
    val content: List<ContentResponse>,
    val page: Int,
    val hasNextPage: Boolean,
)