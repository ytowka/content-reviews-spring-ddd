package com.danilkha.domain.usecase.authentication

import com.danilkha.domain.exception.AuthenticationException
import com.danilkha.domain.exception.UserNotFoundException
import com.danilkha.domain.model.RefreshToken
import com.danilkha.domain.model.TokenPair
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.repository.RefreshTokenRepository
import com.danilkha.domain.token.TokenParser
import java.util.*
import javax.annotation.ManagedBean
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@ManagedBean
class RefreshTokenUseCase(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val accountRepository: AccountRepository,
    private val tokenParser: TokenParser,
) {

    operator fun invoke(token: String): TokenPair {
        try {
            val id: UUID = UUID.fromString(token.split("/")[0])
            val actualRefreshToken = refreshTokenRepository.findById(id)

            if(actualRefreshToken?.token == token){
                val user = accountRepository.findById(id) ?: throw UserNotFoundException(id.toString())
                val newToken = tokenParser.generate(user.login, id, user.role)
                refreshTokenRepository.save(
                    RefreshToken(
                        userId = id,
                        token = newToken.refreshToken,
                        expiresIn = newToken.refreshExpiresIn
                    )
                )
                return newToken
            }else{
                throw Exception("Wrong token")
            }
        }catch (e: Exception) {
            throw AuthenticationException()
        }
    }
}