package com.danilkha.app.security.userdetails

import com.danilkha.domain.authentication.AuthenticatedAccount
import com.danilkha.domain.authentication.AuthenticationProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationProviderImpl : AuthenticationProvider{
    override fun getAccount(): AuthenticatedAccount {
        return SecurityContextHolder.getContext().authentication.principal as AccountUserDetails
    }

}