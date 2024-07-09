package com.danilkha.domain.usecase.user

import com.danilkha.domain.authentication.AuthenticationProvider
import com.danilkha.domain.exception.UserNotFoundException
import com.danilkha.domain.model.Account
import com.danilkha.domain.repository.AccountRepository
import javax.annotation.ManagedBean

@ManagedBean
class GetMeUseCase(
    private val accountRepository: AccountRepository,
    private val authenticationProvider: AuthenticationProvider,
) {

    operator fun invoke(): Account{
        val userId = authenticationProvider.getAccount().id
        return accountRepository.findById(userId) ?:
            throw UserNotFoundException("user with id $userId not found")

    }
}