package com.danilkha.domain.usecase.authentication

import com.danilkha.domain.exception.UserAlreadyRegisteredException
import com.danilkha.domain.model.Account
import com.danilkha.domain.model.RefreshToken
import com.danilkha.domain.model.Role
import com.danilkha.domain.model.TokenPair
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.repository.PasswordEncoder
import com.danilkha.domain.repository.RefreshTokenRepository
import com.danilkha.domain.token.TokenParser
import java.util.*
import javax.annotation.ManagedBean

@ManagedBean
class RegisterUserUseCase(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenParser: TokenParser,
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    operator fun invoke(params: Params): TokenPair{
        val isRegistered = accountRepository.findByLoginOrEmail(params.login, params.email) != null
        if (isRegistered) {
            throw UserAlreadyRegisteredException()
        }

        val id = UUID.randomUUID()
        val account = Account(
            id = id,
            fullName = params.fullName,
            login = params.login,
            email = params.email,
            phone = params.phone,
            password = passwordEncoder.encode(params.password),
            role = Role.USER,
            isBlocked = false,
            avatarFileName = null,
            activated = true
        )
        accountRepository.save(account)

        val token = tokenParser.generate(account.login, id, account.role)
        refreshTokenRepository.save(
            RefreshToken(
                userId = id,
                token = token.refreshToken,
                expiresIn = token.refreshExpiresIn
            )
        )

        return token
    }

    class Params(
        val login: String,
        val password: String,
        val email: String,
        val phone: String,
        val fullName: String,
    )
}