package com.danilkha.contentreviewsbackend.service

import com.danilkha.contentreviews.api.auth.LoginRequest
import com.danilkha.contentreviews.api.auth.RefreshTokenRequest
import com.danilkha.contentreviews.api.auth.RegisterRequest
import com.danilkha.contentreviews.api.auth.TokenPairResponse
import com.danilkha.contentreviewsbackend.model.AccountDto
import com.danilkha.contentreviewsbackend.security.model.TokenRequest

interface AuthenticationService {

    fun userInfoByToken(token: TokenRequest): AccountDto

    fun register(request: RegisterRequest): TokenPairResponse

    fun login(request: LoginRequest): TokenPairResponse

    fun refreshToken(request: RefreshTokenRequest): TokenPairResponse
}