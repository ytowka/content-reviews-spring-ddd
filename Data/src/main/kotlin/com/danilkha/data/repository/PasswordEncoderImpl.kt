package com.danilkha.data.repository

import com.danilkha.domain.repository.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component

@Component
class PasswordEncoderImpl(
) : PasswordEncoder {


    private val passwordEncoder: org.springframework.security.crypto.password.PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun matches(password: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(password, encodedPassword)
    }

}