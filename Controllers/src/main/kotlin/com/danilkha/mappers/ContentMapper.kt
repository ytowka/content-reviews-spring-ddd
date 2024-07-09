package com.danilkha.mappers

import com.danilkha.contentreviews.api.content.ContentListResponse
import com.danilkha.contentreviews.api.content.ContentResponse
import com.danilkha.domain.model.Content
import com.danilkha.domain.model.PagedData

fun Content.toResponse() = ContentResponse(
    id = this.id,
    themeId = this.themeId,
    name = this.name,
    imageUrl = this.image?.asFullPathUrl(),
    avgMark = avg,
    reviewCount = count ?: 0
)

fun PagedData<Content>.toResponse(): ContentListResponse = ContentListResponse(
    content = data.map { it.toResponse() },
    page = page,
    hasNextPage = hasNextPage
)