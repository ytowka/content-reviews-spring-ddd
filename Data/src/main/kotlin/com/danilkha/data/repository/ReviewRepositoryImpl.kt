package com.danilkha.data.repository

import com.danilkha.data.entity.toDto
import com.danilkha.data.entity.toEntity
import com.danilkha.data.jparepository.ReviewJpaRepository
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReviewRepositoryImpl(
    private val reviewJpaRepository: ReviewJpaRepository
): ReviewRepository {
    override fun findByContentIdOrderByWriteDateTimeDesc(reviewId: Long, page: Int): PagedData<Review> {
        val pageRequest = PageRequest.of(DEFAULT_PAGE_SIZE, page)
        val result = reviewJpaRepository.findByContentIdOrderByWriteDateTimeDesc(reviewId, pageRequest)

        return PagedData(
            data = result.toList().map { it.toDto() },
            page = page,
            hasNextPage = result.hasNext()
        )
    }

    override fun findByUserIdOrderByWriteDateTime(userId: UUID, page: Int): PagedData<Review> {
        val pageRequest = PageRequest.of(DEFAULT_PAGE_SIZE, page)
        val result = reviewJpaRepository.findByUserIdOrderByWriteDateTime(userId, pageRequest)
        return PagedData(
            data = result.toList().map { it.toDto() },
            page = page,
            hasNextPage = result.hasNext()
        )
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

    override fun save(review: Review): Review {
        return reviewJpaRepository.save(review.toEntity()).toDto()
    }

    override fun deleteById(id: Long) {
        reviewJpaRepository.deleteById(id)
    }

    override fun getUserRatedTopics(userId: UUID): List<Long> {
        return reviewJpaRepository.getUserRatedTopics(userId)
    }

    companion object{
        private const val DEFAULT_PAGE_SIZE = 10
    }
}