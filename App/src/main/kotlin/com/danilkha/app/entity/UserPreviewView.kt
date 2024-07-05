package com.danilkha.app.entity

import java.util.*

interface UserPreviewView {
    val id: UUID
    val login: String
    val avatarFileName: String?
}