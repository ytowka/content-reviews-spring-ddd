package com.danilkha.domain.usecase.authentication

import com.danilkha.domain.exception.AuthenticationException
import com.danilkha.domain.exception.UserNotFoundException
import com.danilkha.domain.model.RefreshToken
import com.danilkha.domain.model.TokenPair
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.repository.PasswordEncoder
import com.danilkha.domain.repository.RefreshTokenRepository
import com.danilkha.domain.token.TokenParser
import javax.annotation.ManagedBean

@ManagedBean
class LoginUseCase(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenParser: TokenParser
) {
    operator fun invoke(params: Params): TokenPair{
        val accountEntity = accountRepository.getByLogin(params.login) ?: throw UserNotFoundException(params.login)

        if(passwordEncoder.matches(params.password, accountEntity.password)) {
            val newToken = tokenParser.generate(params.login, accountEntity.id, accountEntity.role)
            refreshTokenRepository.save(
                RefreshToken(
                    userId = accountEntity.id,
                    token = newToken.refreshToken,
                    expiresIn = newToken.refreshExpiresIn
                )
            )
            return newToken
        }

        throw AuthenticationException()
    }

    class Params(val login: String,
                 val password: String)
}