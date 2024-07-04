package com.danilkha.contentreviewsbackend.entity

import com.danilkha.contentreviews.api.review.ReviewRequest
import com.danilkha.contentreviews.api.review.ReviewResponse
import com.danilkha.contentreviewsbackend.controller.FILES_PATH
import org.hibernate.annotations.Formula
import org.springframework.http.ResponseEntity
import java.sql.Timestamp
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "review")
data class ReviewEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: UUID,
    val contentId: Long,
    val text: String,
    val mark: Int,
    val writeDateTime: Timestamp
){


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    val userPreview: AccountEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId", nullable = false, insertable = false, updatable = false)
    val content: ContentEntity? = null
}

fun ReviewEntity.toResponse(): ReviewResponse = ReviewResponse(
    id = id,
    userId = userId.toString(),
    contentId = contentId,
    userAvatarUrl = "$FILES_PATH/${userPreview?.avatarFileName}",
    text = text,
    mark = mark,
    userName = userPreview?.login ?: "",
    writeTime = writeDateTime.time,
    contentName = content?.name ?: ""
)

fun ReviewRequest.toEntity(
    id: Long = 0,
    userId: UUID,
    writeTime: Timestamp
): ReviewEntity = ReviewEntity(
    id = id,
    userId = userId,
    contentId = this.contentId,
    text = this.text,
    mark = this.mark,
    writeDateTime = writeTime
)