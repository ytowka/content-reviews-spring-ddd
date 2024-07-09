package com.danilkha.domain.repository

import com.danilkha.domain.model.Account
import com.danilkha.domain.model.PagedData
import java.util.UUID

interface AccountRepository {

    fun save(account: Account): Account
    fun getByLogin(login: String): Account?
    fun findById(uuid: UUID): Account?
    fun findByLoginOrEmail(login: String, email: String): Account?
    fun setAvatar(fileName: String, id: UUID)
    fun findAll(page: Int): PagedData<Account>
}