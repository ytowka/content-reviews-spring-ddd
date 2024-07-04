package com.danilkha.contentreviewsbackend.model

enum class Authority {
    READ, READ_ALL, EDIT, DELETE, CREATE;

    companion object{
        val all: Set<Authority> = Authority.entries.toSet()
    }
}