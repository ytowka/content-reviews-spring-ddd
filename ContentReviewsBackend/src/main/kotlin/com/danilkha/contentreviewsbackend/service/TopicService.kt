package com.danilkha.contentreviewsbackend.service

import com.danilkha.contentreviews.api.content.ContentListResponse
import com.danilkha.contentreviews.api.content.ContentResponse
import com.danilkha.contentreviews.api.theme.ThemeResponse
import com.danilkha.contentreviews.api.users.UserTasteMatchScoreResponse
import java.util.UUID

interface TopicService {

    fun getTopics(): List<ThemeResponse>
    fun getContentById(contentId: Long): ContentResponse

    fun getContent(id: Long, page: Int): ContentListResponse

    fun searchContent(id: Long, query: String): List<ContentResponse>

    fun getRecommendedContent(): List<ContentResponse>

    fun getRecommendedContent(topicId: Long): List<ContentResponse>

    fun getMatchScore(userId: UUID) : UserTasteMatchScoreResponse
}