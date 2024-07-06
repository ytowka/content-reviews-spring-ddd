package com.danilkha.data.repository

import com.danilkha.data.entity.toDto
import com.danilkha.data.entity.toEntity
import com.danilkha.data.jparepository.AccountJpaRepository
import com.danilkha.domain.model.Account
import com.danilkha.domain.repository.AccountRepository
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class AccountRepositoryImpl(
    val accountJpaRepository: AccountJpaRepository
) : AccountRepository {
    override fun save(account: Account): Account {
        return accountJpaRepository.save(account.toEntity()).toDto()
    }

    override fun getByLogin(login: String): Account? {
        return accountJpaRepository.getByLogin(login)?.toDto()
    }

    override fun findById(uuid: UUID): Account? {
        return accountJpaRepository.findById(uuid).getOrNull()?.toDto()
    }

    override fun findByLoginOrEmail(login: String, email: String): Account? {
        return accountJpaRepository.findByLoginOrEmail(login, email)?.toDto()
    }

    override fun setAvatar(fileName: String, id: UUID) {
        return accountJpaRepository.setAvatar(fileName, id)
    }

}