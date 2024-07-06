package com.danilkha.domain.repository

import com.danilkha.domain.model.Account


interface AccountSearchRepository {

    fun searchByLoginOrFullName(login: String, fullName: String): List<Account>
}