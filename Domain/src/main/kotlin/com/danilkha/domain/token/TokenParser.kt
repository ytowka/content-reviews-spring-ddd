package com.danilkha.domain.token

import com.danilkha.domain.model.Role
import com.danilkha.domain.model.TokenPair
import java.util.*

interface TokenParser {
    fun parse(token: String): AuthToken

    fun generate(login: String, id: UUID, role: Role): TokenPair
}