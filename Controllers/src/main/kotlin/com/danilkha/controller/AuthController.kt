package com.danilkha.controller

import com.danilkha.contentreviews.api.auth.*
import com.danilkha.domain.usecase.authentication.LoginUseCase
import com.danilkha.domain.usecase.authentication.RefreshTokenUseCase
import com.danilkha.domain.usecase.authentication.RegisterUserUseCase
import com.danilkha.domain.usecase.user.GetDefaultAvatarUseCase
import com.danilkha.domain.usecase.user.UpdateUserAvatarUseCase
import com.danilkha.mappers.toResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val loginUseCase: LoginUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val updateAvatarUseCase: UpdateUserAvatarUseCase,
    ) : AuthApi{

    @PostMapping("/sign-in")
    override fun login(@RequestBody loginRequest: LoginRequest): TokenPairResponse {
        return loginUseCase(LoginUseCase.Params(
            login = loginRequest.login,
            password = loginRequest.password
        )).toResponse()
    }

    @PostMapping("/refresh-token")
    override fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): TokenPairResponse {
        return refreshTokenUseCase(refreshTokenRequest.token).toResponse()
    }

    @PostMapping("/sign-up")
    override fun register(@RequestBody registerRequest: RegisterRequest): TokenPairResponse {
        return registerUserUseCase(RegisterUserUseCase.Params(
            login = registerRequest.login,
            password = registerRequest.password,
            email = registerRequest.email,
            phone = registerRequest.phone,
            fullName = registerRequest.fullName
        )).toResponse()
    }

    @PostMapping("/sign-up/avatar")
    fun uploadAvatar(@RequestParam("image") file: MultipartFile): LoadFileResult{
        val url = updateAvatarUseCase(
            UpdateUserAvatarUseCase.Params(
                fileName = file.name,
                inputStream = file.inputStream,
                size = file.size
            )
        )
        return LoadFileResult(url)
    }

    @PostMapping("/sign-up/avatar/empty")
    fun uploadAvatar(): LoadFileResult{
        val url = updateAvatarUseCase(null)
        return LoadFileResult(url)
    }
}