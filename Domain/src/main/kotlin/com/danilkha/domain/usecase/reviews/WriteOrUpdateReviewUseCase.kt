package com.danilkha.domain.usecase.reviews

import com.danilkha.domain.authentication.AuthenticationProvider
import com.danilkha.domain.model.Review
import com.danilkha.domain.repository.ReviewRepository
import java.sql.Timestamp
import java.util.*
import javax.annotation.ManagedBean

@ManagedBean
class WriteOrUpdateReviewUseCase(
    private val reviewRepository: ReviewRepository,
    private val authenticationProvider: AuthenticationProvider,
) {

    operator fun invoke(params: Params){
        val user = authenticationProvider.getAccount()

        val review = reviewRepository.getByUserIdAndContentId(user.id, params.contentId)

        val newReview = Review(
            id = review?.id ?: 0,
            userId = user.id,
            contentId = params.contentId,
            contentName = "",
            userAvatarUrl = null,
            userName = "",
            mark = params.mark,
            writeTime = Timestamp(Date().time),
            text = params.text
        )
        reviewRepository.save(newReview)
    }

    class Params(
        val contentId: Long,
        val mark: Int,
        val text: String
    )
}