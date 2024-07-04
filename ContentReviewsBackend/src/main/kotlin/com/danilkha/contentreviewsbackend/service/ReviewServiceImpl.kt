package com.danilkha.contentreviewsbackend.service

import com.danilkha.contentreviews.api.content.ContentListResponse
import com.danilkha.contentreviews.api.review.ReviewListResponse
import com.danilkha.contentreviews.api.review.ReviewRequest
import com.danilkha.contentreviews.api.review.ReviewResponse
import com.danilkha.contentreviewsbackend.entity.ReviewEntity
import com.danilkha.contentreviewsbackend.entity.toEntity
import com.danilkha.contentreviewsbackend.entity.toResponse
import com.danilkha.contentreviewsbackend.exception.ServiceException
import com.danilkha.contentreviewsbackend.repository.ReviewRepository
import com.danilkha.contentreviewsbackend.security.userdetails.AccountUserDetails
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
) : ReviewService {

    override fun getReviewsByContent(contentId: Long, page: Int): ReviewListResponse {
        val pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE)
        val result = reviewRepository.findByContentIdOrderByWriteDateTimeDesc(contentId, pageRequest)
        return ReviewListResponse(
            reviews = result.toList().map(ReviewEntity::toResponse),
            page = page,
            hasNextPage = result.hasNext(),
        )
    }

    override fun getReviewsByUser(userId: UUID, page: Int): ReviewListResponse {
        val pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE)
        val result = reviewRepository.findByUserIdOrderByWriteDateTime(userId, pageRequest)
        return ReviewListResponse(
            reviews = result.toList().map(ReviewEntity::toResponse),
            page = page,
            hasNextPage = result.hasNext(),
        )
    }

    override fun getReviewByUserContent(userId: UUID, contentId: Long): ReviewResponse {
        return reviewRepository.getByUserIdAndContentId(userId, contentId)?.toResponse()
            ?: throw ServiceException(HttpStatus.NOT_FOUND, "Content not found")
    }

    override fun writeReview(reviewRequest: ReviewRequest) {
        val userId = SecurityContextHolder.getContext().authentication.principal as AccountUserDetails

        val review = reviewRepository.getByUserIdAndContentId(userId.id, reviewRequest.contentId)

        reviewRepository.save(reviewRequest.toEntity(
            id = review?.id ?: 0,
            userId = userId.id,
            writeTime = Timestamp(Date().time)
        ))
    }

    override fun editReview(reviewRequest: ReviewRequest) {
        val userId = SecurityContextHolder.getContext().authentication.principal as AccountUserDetails

        val review = reviewRepository.getByUserIdAndContentId(userId.id, reviewRequest.contentId)

        reviewRepository.save(reviewRequest.toEntity(
            id = review?.id ?: 0,
            userId = userId.id,
            writeTime = Timestamp(Date().time)
        ))
    }

    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }

    companion object{
        const val DEFAULT_PAGE_SIZE = 10
    }
}