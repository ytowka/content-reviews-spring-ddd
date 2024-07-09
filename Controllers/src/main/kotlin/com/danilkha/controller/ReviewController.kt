package com.danilkha.controller

import com.danilkha.contentreviews.api.review.ReviewApi
import com.danilkha.contentreviews.api.review.ReviewListResponse
import com.danilkha.contentreviews.api.review.ReviewRequest
import com.danilkha.contentreviews.api.review.ReviewResponse
import com.danilkha.domain.usecase.reviews.*
import com.danilkha.mappers.toResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Api(tags = ["Posts | Посты"], value = "Пост")
@RequestMapping("/api/reviews")
class ReviewController(
    private val getReviewsByContentUseCase: GetReviewsByContentUseCase,
    private val getReviewsByUserUseCase: GetReviewsByUserUseCase,
    private val getReviewsByUserContentUseCase: GetReviewByUserContentUseCase,
    private val writeOrUpdateReviewUseCase: WriteOrUpdateReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    ) : ReviewApi{

    @GetMapping("/content/{contentId}")
    @ApiOperation(
        value = "Получение отзывов по контенту",
        nickname = "get-content-reviews",
        response = ReviewListResponse::class,
        httpMethod = "GET",
        tags = ["reviews"],
        authorizations = [Authorization(value = "jwt")]
    )
    override fun getReviewsByContent(@PathVariable contentId: Long, @RequestParam page: Int): ReviewListResponse {
        return getReviewsByContentUseCase(contentId, page).toResponse()
    }

    @ApiOperation(
        value = "Получение отзывов юзера",
        nickname = "get-user-reviews",
        response = ReviewListResponse::class,
        httpMethod = "GET",
        tags = ["reviews"],
    )
    @GetMapping("/user/{userId}")
    override fun getReviewsByUser(@PathVariable userId: UUID, @RequestParam page: Int): ReviewListResponse {
        return getReviewsByUserUseCase(userId, page).toResponse()
    }

    @ApiOperation(
        value = "Получение отзыва юзера на контент",
        nickname = "get-user-content-review",
        response = ReviewResponse::class,
        httpMethod = "GET",
        tags = ["reviews"],
    )
    @GetMapping("/user/{userId}/content/{contentId}")
    override fun getReviewByUserContent(@PathVariable("userId") userId: UUID, @PathVariable("contentId") contentId: Long): ReviewResponse {
        return getReviewsByUserContentUseCase(userId, contentId).toResponse()
    }

    @ApiOperation(
        value = "Написать отзыв",
        nickname = "write-review",
        httpMethod = "POST",
        tags = ["reviews"],
    )
    @PostMapping
    override fun writeReview(@RequestBody reviewRequest: ReviewRequest) {
        return writeOrUpdateReviewUseCase(reviewRequest.toParam())
    }

    @ApiOperation(
        value = "Редактировать отзыв",
        nickname = "update-review",
        httpMethod = "PUT",
        tags = ["reviews"],
    )
    @PutMapping
    override fun editReview(@RequestBody reviewRequest: ReviewRequest) {
        println("editReview $reviewRequest")
        return writeOrUpdateReviewUseCase(reviewRequest.toParam())
    }

    @ApiOperation(
        value = "Удалить отзыв",
        nickname = "delete-review",
        httpMethod = "DELETE",
        tags = ["reviews"],
    )
    @DeleteMapping("/{reviewId}")
    override fun deleteReview(@PathVariable reviewId: Long) {
        return deleteReviewUseCase(reviewId)
    }

    private fun ReviewRequest.toParam() = WriteOrUpdateReviewUseCase.Params(
        contentId, mark, text
    )
}