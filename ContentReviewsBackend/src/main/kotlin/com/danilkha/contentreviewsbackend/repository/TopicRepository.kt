package com.danilkha.contentreviewsbackend.repository

import com.danilkha.contentreviewsbackend.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


interface TopicRepository : JpaRepository<TopicEntity, Long>