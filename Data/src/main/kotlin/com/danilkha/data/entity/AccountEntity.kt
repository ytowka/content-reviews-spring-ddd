package com.danilkha.data.entity

import com.danilkha.domain.model.Account
import com.danilkha.domain.model.Role
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "account")
data class AccountEntity(
    @Id
    val id: UUID,
    val fullName: String,
    val login: String,
    val avatarFileName: String?,
    val email: String,
    val phone: String,
    val password: String,
    val role: Role,
    val isBlocked: Boolean,
    val activated: Boolean,
)

fun AccountEntity.toDto(): Account = Account(
    id = this.id,
    fullName = this.fullName,
    login = this.login,
    email = this.email,
    phone = this.phone,
    password = this.password,
    role = role,
    isBlocked = isBlocked,
    avatarFileName = avatarFileName,
    activated = activated
)

fun Account.toEntity(): AccountEntity = AccountEntity(
    id = this.id,
    fullName = this.fullName,
    login = this.login,
    email = this.email,
    phone = this.phone,
    password = this.password,
    role = this.role,
    isBlocked = isBlocked,
    avatarFileName = avatarFileName,
    activated = activated
)