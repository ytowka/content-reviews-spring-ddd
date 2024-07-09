package com.danilkha.domain.repository

import com.danilkha.domain.model.Content
import com.danilkha.domain.model.PagedData

interface ContentRepository{

    fun getContentWithReviews(
        topicId: Long,
        page: Int,
    ): PagedData<Content>



    fun searchContentWithReviews(
        topicId: Long,
        query: String
    ): List<Content>


    fun findByIdWithReviews(contentId: Long): Content?
}

