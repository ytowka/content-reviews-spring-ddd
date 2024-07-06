package com.danilkha.app.security.userdetails

import com.danilkha.domain.model.Account
import com.danilkha.app.security.exception.AuthenticationHeaderException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service


@Service
class TokenAuthenticationUserDetailsService : AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    override fun loadUserDetails(token: PreAuthenticatedAuthenticationToken): UserDetails {
        return loadUserDetails(token.principal as? Account, token.credentials.toString())
    }

    private fun loadUserDetails(account: Account?, token: String): UserDetails {
        return try {
            account?.run {
                val authorities: List<SimpleGrantedAuthority> = (account.role.authorities + account.role)
                    .map { authority ->
                        SimpleGrantedAuthority(authority.name)
                    }
                AccountUserDetails(
                    id = account.id,
                    authorities = authorities.toMutableList(),
                    password = password,
                    username = login,
                    isCredentialsNonExpired = true,
                    isAccountNonLocked = !account.isBlocked,
                    isEnabled = true,
                    isAccountNonExpired = true,
                )
            } ?: throw UsernameNotFoundException(
                "Unknown user by token $token"
            )
        } catch (exception: Exception) {
            throw AuthenticationHeaderException(exception.message)
        }
    }
}