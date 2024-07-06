package com.danilkha.domain.repository

interface PasswordEncoder {
    fun encode(password: String): String
    fun matches(password: String, encodedPassword: String): Boolean
}