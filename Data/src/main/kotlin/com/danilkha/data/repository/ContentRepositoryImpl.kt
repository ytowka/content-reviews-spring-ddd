package com.danilkha.data.repository

import com.danilkha.data.entity.toDto
import com.danilkha.data.jparepository.ContentJpaRepository
import com.danilkha.domain.model.Content
import com.danilkha.domain.repository.ContentRepository
import org.springframework.stereotype.Component

@Component
class ContentRepositoryImpl(
    private val contentJpaRepository: ContentJpaRepository
): ContentRepository {
    override fun getContentWithReviews(topicId: Long, offset: Int, size: Int): List<Content> {
        return contentJpaRepository.getContentWithReviews(topicId, offset, size).map {
            it.toDto()
        }
    }

    override fun searchContentWithReviews(topicId: Long, query: String): List<Content> {
        return contentJpaRepository.searchContentWithReviews(topicId, query).map { it.toDto() }
    }

    override fun findByIdWithReviews(contentId: Long): Content? {
        return contentJpaRepository.findByIdWithReviews(contentId)?.toDto()
    }
}