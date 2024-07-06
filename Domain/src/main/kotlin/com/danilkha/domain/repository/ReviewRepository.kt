package com.danilkha.domain.repository

import com.danilkha.domain.model.Review

import java.util.UUID

interface ReviewRepository {

    fun findByContentIdOrderByWriteDateTimeDesc(reviewId: Long, pageSize: Int, page: Int): List<Review>

    fun findByUserIdOrderByWriteDateTime(userId: UUID, pageSize: Int, page: Int): List<Review>

    fun getReviewsByContentId(contentId: Long): List<Review>

    fun getReviewsByUserId(userId: UUID): List<Review>

    fun getByUserIdAndContentId(userId: UUID, contentId: Long): Review?


    fun getUserRatedTopics(userId: UUID) : List<Long>
}