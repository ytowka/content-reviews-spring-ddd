package com.danilkha.domain.usecase.topic

import com.danilkha.domain.model.Content
import com.danilkha.domain.repository.ContentRepository
import javax.annotation.ManagedBean

@ManagedBean
class SearchContentUseCase(
    private val contentRepository: ContentRepository
) {

    operator fun invoke(id: Long, query: String): List<Content>{
        return contentRepository.searchContentWithReviews(id, query)
    }
}