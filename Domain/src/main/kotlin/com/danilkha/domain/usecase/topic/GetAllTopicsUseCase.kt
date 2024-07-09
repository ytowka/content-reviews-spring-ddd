package com.danilkha.domain.usecase.topic

import com.danilkha.domain.repository.TopicRepository
import javax.annotation.ManagedBean

@ManagedBean
class GetAllTopicsUseCase(
    private val topicRepository: TopicRepository
) {
    operator fun invoke() = topicRepository.findAll()
}