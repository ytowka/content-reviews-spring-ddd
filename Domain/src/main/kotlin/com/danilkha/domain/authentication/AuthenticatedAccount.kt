package com.danilkha.domain.authentication

import java.util.*

interface AuthenticatedAccount {

    val id: UUID
    val passwordEncoded: String
    val userName: String
    val isNotExpired: Boolean
    val isNotLocked: Boolean
    val isCredentialsNotExpired: Boolean
    val isActive: Boolean
}