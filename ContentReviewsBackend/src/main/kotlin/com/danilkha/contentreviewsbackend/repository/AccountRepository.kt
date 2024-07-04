package com.danilkha.contentreviewsbackend.repository

import com.danilkha.contentreviewsbackend.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID
import javax.transaction.Transactional

interface AccountRepository : JpaRepository<AccountEntity, UUID>, AccountSearchRepository{

    fun getByLogin(login: String): AccountEntity?
    fun findByLoginOrEmail(login: String, email: String): AccountEntity?

    @Modifying
    @Transactional
    @Query("update AccountEntity account set account.avatarFileName = ?1 where account.id = ?2")
    fun setAvatar(fileName: String, id: UUID)
}