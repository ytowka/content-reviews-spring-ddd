package com.danilkha.data.jparepository

import com.danilkha.data.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.transaction.Transactional

@Repository
interface AccountJpaRepository : JpaRepository<AccountEntity, UUID> {

    fun getByLogin(login: String): AccountEntity?
    fun findByLoginOrEmail(login: String, email: String): AccountEntity?

    @Modifying
    @Transactional
    @Query("update AccountEntity account set account.avatarFileName = ?1 where account.id = ?2")
    fun setAvatar(fileName: String, id: UUID)
}