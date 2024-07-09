package com.danilkha.app.security.userdetails

import com.danilkha.domain.authentication.AuthenticatedAccount
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class AccountUserDetails(
    override val id: UUID,
    private  val authorities: MutableCollection<GrantedAuthority>,
    override val passwordEncoded: String,
    override val userName: String,
    override val isActive: Boolean,
    override val isNotExpired: Boolean,
    override val isNotLocked: Boolean,
    override val isCredentialsNotExpired: Boolean,
) : UserDetails, AuthenticatedAccount {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities;
    }

    override fun getPassword(): String = passwordEncoded

    override fun getUsername(): String = userName

    override fun isAccountNonExpired(): Boolean = isAccountNonExpired

    override fun isAccountNonLocked(): Boolean = isAccountNonLocked

    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired

    override fun isEnabled(): Boolean = isActive

}