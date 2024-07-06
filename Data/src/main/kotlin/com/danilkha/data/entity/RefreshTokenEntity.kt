package com.danilkha.data.entity

import com.danilkha.domain.model.RefreshToken
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "refresh_token")
data class RefreshTokenEntity(
    @Id
    val userId: UUID,
    val token: String,
    val expiresIn: Long
)

fun RefreshTokenEntity.toRefreshToken() = RefreshToken(userId, token, expiresIn)

fun RefreshToken.toEntity() = RefreshTokenEntity(userId, token, expiresIn)