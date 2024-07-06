package com.danilkha.domain.repository

import com.danilkha.domain.model.Content

interface ContentRepository{

    fun getContentWithReviews(
        topicId: Long,
        offset: Int,
        size: Int
    ): List<Content>



    fun searchContentWithReviews(
        topicId: Long,
        query: String
    ): List<Content>


    fun findByIdWithReviews(contentId: Long): Content?
}

