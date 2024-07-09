package com.danilkha.app.security.userdetails

import com.danilkha.domain.authentication.AuthenticatedAccount
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class AccountUserDetails(
    override val id: UUID,
    private val authorities: MutableCollection<GrantedAuthority>,
    override val password: String,
    override val username: String,
    override val isAccountNonExpired: Boolean,
    override val isAccountNonLocked: Boolean,
    override val isCredentialsNonExpired: Boolean,
    override val isEnabled: Boolean,
) : UserDetails, AuthenticatedAccount {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities;
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = isAccountNonExpired

    override fun isAccountNonLocked(): Boolean = isAccountNonLocked

    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired

    override fun isEnabled(): Boolean = isEnabled

}