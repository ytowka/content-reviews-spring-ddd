package com.danilkha.domain.model

import java.util.*

data class RefreshToken (
    val userId: UUID,
    val token: String,
    val expiresIn: Long
)