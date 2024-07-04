package com.danilkha.contentreviewsbackend.entity

import com.danilkha.contentreviews.api.content.ContentResponse
import com.danilkha.contentreviewsbackend.controller.FILES_PATH
import javax.persistence.*

@Entity
@Table(name = "content")
data class ContentEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val themeId: Long,
    val name: String,
    val image: String?,
)

fun ContentEntity.toResponse(
    avgMark: Float,
    reviewCount: Int,
): ContentResponse = ContentResponse(
    id = id,
    themeId = themeId,
    name = name,
    imageUrl = image?.let { "$FILES_PATH/$it" },
    avgMark = avgMark,
    reviewCount = reviewCount
)