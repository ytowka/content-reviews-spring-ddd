package com.danilkha.app.service

import com.danilkha.contentreviews.api.auth.LoadFileResult
import com.danilkha.contentreviews.api.users.UserListResponse
import com.danilkha.contentreviews.api.users.UserRequest
import com.danilkha.contentreviews.api.users.UserResponse
import org.springframework.web.multipart.MultipartFile

interface UserService{

    fun getAll(page: Int): UserListResponse
    fun search(query: String): List<UserResponse>
    fun getById(id: String): UserResponse
    fun update(user: UserRequest): UserResponse
    fun updateAvatar(file: MultipartFile?): LoadFileResult
    fun getMe(): UserResponse
}
