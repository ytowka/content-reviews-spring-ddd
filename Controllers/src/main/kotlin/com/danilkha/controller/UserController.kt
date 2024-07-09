package com.danilkha.controller

import com.danilkha.contentreviews.api.users.*
import com.danilkha.domain.usecase.user.*
import com.danilkha.mappers.toResponse
import com.danilkha.mappers.toRole
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val searchUserUseCase: SearchUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getMeUseCase: GetMeUseCase,
) : UserApi{


    @GetMapping(params = ["page"])
    override fun getAll(@RequestParam page: Int): UserListResponse {
        return getAllUsersUseCase(page).toResponse()
    }

    @GetMapping(params = ["q"])
    override fun search(@RequestParam q: String): List<UserResponse> {
        return searchUserUseCase(q).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    override fun get(@PathVariable id: String): UserResponse {
        return getUserByIdUseCase(UUID.fromString(id)).toResponse()
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    override fun update(@RequestBody user: UserRequest) {
       updateUserUseCase(
           UpdateUserUseCase.Params(
               id = user.id,
               fullName = user.fullName,
               email = user.email,
               phone = user.phone,
               role = user.role.toRole(),
               isBlocked = false
           )
       )
    }

    @GetMapping("/me")
    override fun getMe(): UserResponse {
        return getMeUseCase().toResponse()
    }
}