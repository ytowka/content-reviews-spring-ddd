package com.danilkha.data.repository

import com.danilkha.data.entity.toDto
import com.danilkha.data.jparepository.ReviewJpaRepository
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReviewRepositoryImpl(
    private val reviewJpaRepository: ReviewJpaRepository
): ReviewRepository {
    override fun findByContentIdOrderByWriteDateTimeDesc(reviewId: Long, pageSize: Int, page: Int): List<Review> {
        return reviewJpaRepository.findByContentIdOrderByWriteDateTimeDesc(reviewId, PageRequest.of(pageSize, page)).map {
            it.toDto()
        }.content
    }

    override fun findByUserIdOrderByWriteDateTime(userId: UUID, pageSize: Int, page: Int): List<Review> {
        return reviewJpaRepository.findByUserIdOrderByWriteDateTime(userId, PageRequest.of(pageSize, page)).map {
            it.toDto()
        }.content
    }

    override fun getReviewsByContentId(contentId: Long): List<Review> {
        return reviewJpaRepository.getReviewsByContentId(contentId).map { it.toDto() }
    }

    override fun getReviewsByUserId(userId: UUID): List<Review> {
        return reviewJpaRepository.getReviewsByUserId(userId).map { it.toDto() }
    }

    override fun getByUserIdAndContentId(userId: UUID, contentId: Long): Review? {
        return reviewJpaRepository.getByUserIdAndContentId(userId, contentId)?.toDto()
    }

    override fun getUserRatedTopics(userId: UUID): List<Long> {
        return reviewJpaRepository.getUserRatedTopics(userId)
    }
}