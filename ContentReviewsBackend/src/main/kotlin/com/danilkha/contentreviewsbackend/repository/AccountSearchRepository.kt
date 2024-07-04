package com.danilkha.contentreviewsbackend.repository

import com.danilkha.contentreviewsbackend.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID
import javax.transaction.Transactional

interface AccountSearchRepository {

    fun searchByLoginOrFullName(login: String, fullName: String): List<AccountEntity>
}