package com.danilkha.contentreviewsbackend.service

import com.danilkha.contentreviews.api.review.ReviewListResponse
import com.danilkha.contentreviews.api.review.ReviewRequest
import com.danilkha.contentreviews.api.review.ReviewResponse
import java.util.*

interface ReviewService {
    fun getReviewsByContent(contentId: Long, page: Int): ReviewListResponse
    fun getReviewsByUser(userId: UUID, page: Int): ReviewListResponse
    fun getReviewByUserContent(userId: UUID, contentId: Long) : ReviewResponse
    fun writeReview(reviewRequest: ReviewRequest)
    fun editReview(reviewRequest: ReviewRequest)
    fun deleteReview(reviewId: Long)
}