package com.danilkha.data.repository

import com.danilkha.data.entity.toEntity
import com.danilkha.data.entity.toRefreshToken
import com.danilkha.data.jparepository.RefreshTokenJpaRepository
import com.danilkha.domain.model.RefreshToken
import com.danilkha.domain.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class RefreshTokenRepositoryImpl (
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository
): RefreshTokenRepository {
    override fun findById(id: UUID): RefreshToken? {
        return refreshTokenJpaRepository.findByIdOrNull(id)?.toRefreshToken()
    }

    override fun save(refreshToken: RefreshToken) {
        refreshTokenJpaRepository.save(refreshToken.toEntity())
    }
}