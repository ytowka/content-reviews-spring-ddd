package com.danilkha.domain.usecase.reviews

import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import javax.annotation.ManagedBean

@ManagedBean
class GetReviewsByContentUseCase(
    private val reviewRepository: ReviewRepository
) {
    operator fun invoke(contentId: Long, page: Int): PagedData<Review>{
        return reviewRepository.findByContentIdOrderByWriteDateTimeDesc(contentId, page)
    }
}