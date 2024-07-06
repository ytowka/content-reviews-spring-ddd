package com.danilkha.data.repository

import com.danilkha.data.entity.toDto
import com.danilkha.data.jparepository.TopicJpaRepository
import com.danilkha.domain.model.Topic
import com.danilkha.domain.repository.TopicRepository
import org.springframework.stereotype.Component

@Component
class TopicRepositoryImpl(
    private val topicJpaRepository: TopicJpaRepository
) : TopicRepository {
    override fun findAll(): List<Topic> {
        return topicJpaRepository.findAll().map {
            it.toDto()
        }
    }
}