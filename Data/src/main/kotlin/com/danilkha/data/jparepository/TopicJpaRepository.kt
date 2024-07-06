package com.danilkha.data.jparepository

import com.danilkha.data.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository


interface TopicJpaRepository : JpaRepository<TopicEntity, Long>