package com.danilkha.contentreviews.api.review

import kotlinx.serialization.Serializable

@Serializable
data class ReviewListResponse(
    val reviews: List<ReviewResponse>,
    val page: Int,
    val hasNextPage: Boolean,
)