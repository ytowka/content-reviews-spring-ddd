package com.danilkha.contentreviewsbackend.repository

import com.danilkha.contentreviewsbackend.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, UUID>