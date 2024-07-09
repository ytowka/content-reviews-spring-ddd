package com.danilkha.domain.usecase.topic

import com.danilkha.domain.model.Content
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.repository.ContentRepository
import javax.annotation.ManagedBean

@ManagedBean
class GetAllContentUseCase(
    private val contentRepository: ContentRepository
) {

    operator fun invoke(id: Long, page: Int): PagedData<Content>{
        return contentRepository.getContentWithReviews(id, page)
    }
}