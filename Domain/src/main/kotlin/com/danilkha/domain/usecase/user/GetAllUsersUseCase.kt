package com.danilkha.domain.usecase.user;

import com.danilkha.domain.model.Account
import com.danilkha.domain.model.PagedData
import com.danilkha.domain.repository.AccountRepository
import javax.annotation.ManagedBean;

@ManagedBean
class GetAllUsersUseCase(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(page: Int): PagedData<Account> {
        return accountRepository.findAll(page)
    }
}
