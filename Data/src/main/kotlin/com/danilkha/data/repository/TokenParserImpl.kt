package com.danilkha.data.repository

import com.danilkha.data.SecurityConsts
import com.danilkha.data.entity.RefreshTokenEntity
import com.danilkha.domain.model.Role
import com.danilkha.domain.model.TokenPair
import com.danilkha.domain.token.AuthToken
import com.danilkha.domain.token.TokenParser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class TokenParserImpl(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration.access.mills}") private val expirationAccessInMills: Long = 180_000,
    @Value("\${jwt.expiration.refresh.mills}") private val expirationRefreshInMills: Long = 1_800_000,
) : TokenParser {
    override fun parse(token: String): AuthToken {
        val claims: Claims = parseAccessToken(token)
        return AuthToken(claims.subject)
    }

    override fun generate(login: String, id: UUID, role: Role): TokenPair {
        val claims = mutableMapOf(
            SecurityConsts.ROLE to role.name,
        )
        claims[Claims.SUBJECT] = login
        val now = System.currentTimeMillis()
        val jwt = Jwts.builder()
            .claims(claims)
            .issuedAt(Date(now))
            .expiration(Date(now + expirationAccessInMills))
            .signWith(getSecretKey())
            .compact()

        val refresh = "$id/${UUID.randomUUID()}"

        return TokenPair(
            accessToken = jwt,
            refreshToken = refresh,
            refreshExpiresIn = now + expirationRefreshInMills
        )
    }

    private fun parseAccessToken(accessToken: String): Claims {
        return Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(accessToken)
            .payload
    }

    private fun getSecretKey(): SecretKey {
        val base64secret = Base64.getEncoder().encode(jwtSecret.toByteArray())
        return Keys.hmacShaKeyFor(base64secret)
    }
}