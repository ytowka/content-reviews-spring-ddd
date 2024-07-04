package com.danilkha.contentreviews.api.auth

interface AuthApi {

    fun register(registerRequest: RegisterRequest): TokenPairResponse
    fun login(loginRequest: LoginRequest): TokenPairResponse
    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): TokenPairResponse
}