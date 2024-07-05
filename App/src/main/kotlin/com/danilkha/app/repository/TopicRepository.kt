package com.danilkha.app.repository

import com.danilkha.app.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository


interface TopicRepository : JpaRepository<TopicEntity, Long>