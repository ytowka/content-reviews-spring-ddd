package com.danilkha.domain.model

data class PagedData<T>(
    val data: List<T>,
    val page: Int,
    val hasNextPage: Boolean,
)
