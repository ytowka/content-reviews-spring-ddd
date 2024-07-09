package com.danilkha.mappers

import com.danilkha.contentreviews.api.review.ReviewListResponse
import com.danilkha.contentreviews.api.review.ReviewResponse
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Review

fun Review.toResponse() = ReviewResponse(
    id = this.id,
    userId = this.userId.toString(),
    contentId = this.contentId,
    contentName = contentName,
    userAvatarUrl = userAvatarUrl?.asFullPathUrl(),
    userName = userName,
    mark = mark,
    writeTime = writeTime.time,
    text = text
)

fun PagedData<Review>.toResponse() = ReviewListResponse(
    reviews = this.data.map { it.toResponse() },
    page = page,
    hasNextPage = hasNextPage
)
