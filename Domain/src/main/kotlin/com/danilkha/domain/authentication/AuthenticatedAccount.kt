package com.danilkha.domain.authentication

import java.util.*

interface AuthenticatedAccount {

    val id: UUID
    val password: String
    val username: String
    val isAccountNonExpired: Boolean
    val isAccountNonLocked: Boolean
    val isCredentialsNonExpired: Boolean
    val isEnabled: Boolean
}