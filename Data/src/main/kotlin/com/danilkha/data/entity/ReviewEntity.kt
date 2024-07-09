package com.danilkha.data.entity

import com.danilkha.domain.model.Review
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

fun ReviewEntity.toDto(): Review = Review(
    id = id,
    userId = userId,
    contentId = contentId,
    userAvatarUrl = userPreview?.avatarFileName,
    text = text,
    mark = mark,
    userName = userPreview?.login ?: "",
    writeTime = writeDateTime,
    contentName = content?.name ?: ""
)

fun Review.toEntity(): ReviewEntity = ReviewEntity(
    id = id,
    userId = this.userId,
    contentId = this.contentId,
    text = this.text,
    mark = this.mark,
    writeDateTime = writeTime
)