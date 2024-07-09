package com.danilkha.mappers

import com.danilkha.contentreviews.api.theme.ThemeResponse
import com.danilkha.domain.model.Topic

fun Topic.toResponse() = ThemeResponse(
    id = id,
    name = name,
    contentCount = contentCount,
    imageUrl = imageUrl
)