package com.danilkha.data.repository

import com.danilkha.app.service.UserServiceImpl.Companion.DEFAULT_PAGE_SIZE
import com.danilkha.data.entity.toDto
import com.danilkha.data.entity.toEntity
import com.danilkha.data.jparepository.AccountJpaRepository
import com.danilkha.domain.model.Account
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.repository.AccountRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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

    override fun findAll(page: Int): PagedData<Account> {
        val pageable: Pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE)
        val pageResponse = accountJpaRepository.findAll(pageable)
        return PagedData(
            data = pageResponse.content.map { it.toDto() },
            page = page,
            hasNextPage = pageResponse.hasNext(),
        )
    }

}