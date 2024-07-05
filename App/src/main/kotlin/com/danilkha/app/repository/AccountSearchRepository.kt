package com.danilkha.app.repository

import com.danilkha.app.entity.AccountEntity

interface AccountSearchRepository {

    fun searchByLoginOrFullName(login: String, fullName: String): List<AccountEntity>
}