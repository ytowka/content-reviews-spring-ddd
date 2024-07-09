package com.danilkha.domain.usecase.user

import com.danilkha.domain.model.Account
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.repository.AccountSearchRepository
import javax.annotation.ManagedBean

@ManagedBean
class SearchUserUseCase(
    private val accountSearchRepository: AccountSearchRepository,
) {
    operator fun invoke(query: String): List<Account> {
        return accountSearchRepository.searchByLoginOrFullName(query, query)
    }
}