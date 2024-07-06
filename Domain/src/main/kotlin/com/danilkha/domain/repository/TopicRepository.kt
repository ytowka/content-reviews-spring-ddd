package com.danilkha.domain.repository

import com.danilkha.domain.model.Topic


interface TopicRepository{
    fun findAll(): List<Topic>
}