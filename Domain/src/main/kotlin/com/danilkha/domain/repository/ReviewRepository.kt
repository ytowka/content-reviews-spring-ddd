package com.danilkha.domain.repository

import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Review

import java.util.UUID

interface ReviewRepository {

    fun findByContentIdOrderByWriteDateTimeDesc(reviewId: Long,page: Int): PagedData<Review>

    fun findByUserIdOrderByWriteDateTime(userId: UUID, page: Int): PagedData<Review>

    fun getReviewsByContentId(contentId: Long): List<Review>

    fun getReviewsByUserId(userId: UUID): List<Review>

    fun getByUserIdAndContentId(userId: UUID, contentId: Long): Review?

    fun save(review: Review): Review

    fun deleteById(id: Long)

    fun getUserRatedTopics(userId: UUID) : List<Long>
}