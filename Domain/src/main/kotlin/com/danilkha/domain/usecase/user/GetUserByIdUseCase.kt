package com.danilkha.domain.usecase.user

import com.danilkha.domain.exception.UserNotFoundException
import com.danilkha.domain.model.Account
import com.danilkha.domain.repository.AccountRepository
import java.util.UUID
import javax.annotation.ManagedBean

@ManagedBean
class GetUserByIdUseCase(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(id: UUID): Account{
        return accountRepository.findById(id) ?: throw UserNotFoundException(id.toString())
    }
}