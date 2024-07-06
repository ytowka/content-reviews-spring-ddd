package com.danilkha.mappers

import com.danilkha.contentreviews.api.auth.TokenPairResponse
import com.danilkha.domain.model.TokenPair


fun TokenPair.toResponse(): TokenPairResponse = TokenPairResponse(
    accessToken, refreshToken
)