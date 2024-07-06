package com.danilkha.data.jparepository

import com.danilkha.data.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenEntity, UUID>