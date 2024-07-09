package com.danilkha.domain.usecase.reviews

import com.danilkha.domain.repository.ReviewRepository
import javax.annotation.ManagedBean

@ManagedBean
class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    operator fun invoke(reviewId: Long){
        reviewRepository.deleteById(reviewId)
    }
}