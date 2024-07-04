package com.danilkha.contentreviews

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse(
    val status: Int,
    val error: String,
    val message: String?,
) {
}