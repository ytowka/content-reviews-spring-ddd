package com.danilkha.domain.usecase.reviews

import com.danilkha.domain.exception.ServiceException
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import java.util.*
import javax.annotation.ManagedBean

@ManagedBean
class GetReviewByUserContentUseCase(
    private val reviewRepository: ReviewRepository
) {

    operator fun invoke(userId: UUID, contentId: Long): Review {
        return reviewRepository.getByUserIdAndContentId(userId, contentId)
            ?: throw ServiceException(404, "Content not found")
    }
}