package com.danilkha.contentreviewsbackend.entity

import com.danilkha.contentreviewsbackend.model.Role
import java.util.*

interface UserPreviewView {
    val id: UUID
    val login: String
    val avatarFileName: String?
}