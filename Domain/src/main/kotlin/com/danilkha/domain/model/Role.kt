package com.danilkha.domain.model


enum class Role(
    val authorities: Set<Authority>
) {
    USER(setOf(Authority.READ)),
    ADMIN(Authority.all)
}

