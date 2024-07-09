package com.danilkha.domain.usecase.topic

import com.danilkha.domain.exception.NotFoundExceptionException
import com.danilkha.domain.model.Content
import com.danilkha.domain.repository.ContentRepository
import javax.annotation.ManagedBean

@ManagedBean
class GetContentByIdUseCase(
    private val contentRepository: ContentRepository
) {

    operator fun invoke(contentId: Long): Content{
        return contentRepository.findByIdWithReviews(contentId) ?: throw NotFoundExceptionException("content", contentId.toString())
    }


}