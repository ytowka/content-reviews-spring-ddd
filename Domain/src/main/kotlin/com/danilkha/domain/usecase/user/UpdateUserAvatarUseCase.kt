package com.danilkha.domain.usecase.user

import com.danilkha.domain.authentication.AuthenticationProvider
import com.danilkha.domain.repository.AccountRepository
import com.danilkha.domain.usecase.storage.SaveFileUseCase
import java.io.InputStream
import javax.annotation.ManagedBean

@ManagedBean
class UpdateUserAvatarUseCase(
    private val accountRepository: AccountRepository,
    private val saveFileUseCase: SaveFileUseCase,
    private val authenticationProvider: AuthenticationProvider,
    private val getDefaultAvatarUseCase: GetDefaultAvatarUseCase,
) {

    operator fun invoke(
        params: Params?
    ): String{
        val user = authenticationProvider.getAccount()

        val fileName: String = if(params != null){
            saveFileUseCase(
                fileName = params.fileName,
                inputStream = params.inputStream,
                size = params.size
            )
        }else{
            getDefaultAvatarUseCase(user.username)
        }

        accountRepository.setAvatar(fileName, user.id)

        return fileName
    }

    class Params(
        val fileName: String,
        val inputStream: InputStream,
        val size: Long
    )
}