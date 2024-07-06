package com.danilkha.domain.model

class TokenPair (
    val accessToken: String,
    val refreshToken: String,
    val refreshExpiresIn: Long
)