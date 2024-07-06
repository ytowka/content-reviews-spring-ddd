package com.danilkha.domain.model

data class Content(
    val id: Long,
    val themeId: Long,
    val name: String,
    val image: String?,
    val count: Int?,
    val avg: Float?,
)