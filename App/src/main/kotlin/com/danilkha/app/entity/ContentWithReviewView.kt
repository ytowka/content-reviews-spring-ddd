package com.danilkha.app.entity

import com.danilkha.contentreviews.api.content.ContentResponse
import com.danilkha.app.controller.FILES_PATH

interface ContentWithReviewView {
    val id: Long
    val themeId: Long
    val name: String
    val image: String?
    val count: Int?
    val avg: Float?
}

fun ContentWithReviewView.toResponse(): ContentResponse = ContentResponse(
    id = this.id,
    themeId = this.themeId,
    name = this.name,
    imageUrl = "$FILES_PATH/$image",
    avgMark = avg,
    reviewCount = count ?: 0
)