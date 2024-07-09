package com.danilkha.domain.usecase.reviews

import com.danilkha.domain.model.PagedData
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import java.util.*
import javax.annotation.ManagedBean

@ManagedBean
class GetReviewsByUserUseCase(
    private val reviewRepository: ReviewRepository
) {
    operator fun invoke(userId: UUID, page: Int): PagedData<Review>{
        return reviewRepository.findByUserIdOrderByWriteDateTime(userId,page)
    }
}