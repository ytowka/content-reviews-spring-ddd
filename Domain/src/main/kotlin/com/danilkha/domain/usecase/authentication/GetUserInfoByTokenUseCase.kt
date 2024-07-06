package com.danilkha.domain.usecase.authentication

import com.danilkha.domain.model.Account
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.token.TokenParser
import javax.annotation.ManagedBean

@ManagedBean
class GetUserInfoByTokenUseCase(
    private val tokenParser: TokenParser,
    private val accountRepository: AccountRepository
) {
    operator fun invoke(token: String): Account? {
        val token = tokenParser.parse(token)
        return accountRepository.getByLogin(token.login)
    }
}

