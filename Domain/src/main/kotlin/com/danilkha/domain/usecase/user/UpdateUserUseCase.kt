package com.danilkha.domain.usecase.user

import com.danilkha.domain.exception.UserNotFoundException
import com.danilkha.domain.model.Account
import com.danilkha.domain.model.Role
import com.danilkha.domain.repository.AccountRepository
import java.util.*
import javax.annotation.ManagedBean
import kotlin.jvm.optionals.getOrElse

@ManagedBean
class UpdateUserUseCase(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(params: Params): Account{
        val saved = accountRepository.findById(UUID.fromString(params.id))?:
            throw UserNotFoundException("user with id ${params.id} not found")


        val updated = saved.copy(
            fullName = params.fullName,
            email = params.email,
            phone = params.phone,
            role = params.role,
            isBlocked = params.isBlocked,
        )
        return accountRepository.save(updated)
    }

    class Params(
        val id: String,
        val fullName: String,
        val email: String,
        val phone: String,
        val role: Role,
        val isBlocked: Boolean
    )
}