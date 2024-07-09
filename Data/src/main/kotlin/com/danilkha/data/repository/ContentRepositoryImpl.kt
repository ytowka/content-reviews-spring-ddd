package com.danilkha.data.repository

import com.danilkha.data.entity.ContentWithReviewView
import com.danilkha.data.entity.toDto
import com.danilkha.data.jparepository.ContentJpaRepository
import com.danilkha.domain.model.Content
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.repository.ContentRepository
import org.springframework.stereotype.Component

@Component
class ContentRepositoryImpl(
    private val contentJpaRepository: ContentJpaRepository
): ContentRepository {
    override fun getContentWithReviews(topicId: Long, page: Int): PagedData<Content> {
        val offset = page * DEFAULT_PAGE_SIZE
        val pageResponse = contentJpaRepository.getContentWithReviews(topicId, offset, DEFAULT_PAGE_SIZE)


        return PagedData(
            data = pageResponse.map(ContentWithReviewView::toDto),
            page = page,
            hasNextPage = pageResponse.size >= DEFAULT_PAGE_SIZE,
        )
    }

    override fun searchContentWithReviews(topicId: Long, query: String): List<Content> {
        return contentJpaRepository.searchContentWithReviews(
            topicId,
            "%$query%"
        ).map { it.toDto() }
    }

    override fun findByIdWithReviews(contentId: Long): Content? {
        return contentJpaRepository.findByIdWithReviews(contentId)?.toDto()
    }
}