package com.danilkha.domain.repository

import com.danilkha.domain.model.RefreshToken
import java.util.UUID


interface RefreshTokenRepository{
    fun findById(id: UUID): RefreshToken?

    fun save(refreshToken: RefreshToken)
}