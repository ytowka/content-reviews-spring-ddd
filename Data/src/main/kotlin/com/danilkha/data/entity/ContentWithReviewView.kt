package com.danilkha.data.entity

import com.danilkha.domain.model.Content

interface ContentWithReviewView {
    val id: Long
    val themeId: Long
    val name: String
    val image: String?
    val count: Int?
    val avg: Float?
}

fun ContentWithReviewView.toDto(): Content = Content(
    id = this.id,
    themeId = this.themeId,
    name = this.name,
    image = image,
    avg = avg,
    count = count ?: 0
)