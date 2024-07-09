package com.danilkha.domain.authentication

interface AuthenticationProvider {

    fun getAccount(): AuthenticatedAccount
}