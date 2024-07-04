package com.danilkha.contentreviewsbackend.security.util

import com.danilkha.contentreviews.api.auth.SecurityConsts
import com.danilkha.contentreviewsbackend.security.exception.AuthenticationHeaderException
import com.danilkha.contentreviewsbackend.security.model.TokenRequest

object HttpSettingUtil {


    fun getTokenFromAuthorizationHeader(authorizationHeader: String?): String? {
        if(authorizationHeader.isNullOrBlank()) return null
        return authorizationHeader
            .removePrefix(SecurityConsts.BEARER)
            .trim()
            .takeIf { it.isNotBlank() }
    }

    fun getTokenFromValidatedAuthorizationHeader(authorizationHeader: String?): TokenRequest? {
        if (authorizationHeader == null) {
            return null
        }


        if (!authorizationHeader.startsWith(SecurityConsts.BEARER)) {
            throw AuthenticationHeaderException("Invalid authentication scheme found in Authorization header")
        }

        val token = getTokenFromAuthorizationHeader(authorizationHeader)
            ?: throw AuthenticationHeaderException("Authorization header token is empty")

        return TokenRequest(token)
    }
}