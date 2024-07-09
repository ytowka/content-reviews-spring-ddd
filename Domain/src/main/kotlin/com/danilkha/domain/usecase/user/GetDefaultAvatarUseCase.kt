package com.danilkha.domain.usecase.user

import com.danilkha.domain.usecase.storage.SaveImageFromUseCase
import java.awt.Image
import java.net.URL
import java.util.*
import javax.annotation.ManagedBean

@ManagedBean
class GetDefaultAvatarUseCase(
    private val saveImageFromUseCase: SaveImageFromUseCase
){

    operator fun invoke(name: String): String{
        val nameRequest = name.replace(" ","+")
        val url = URL("https://ui-avatars.com/api/?name=$nameRequest&background=random&format=png&bold=true")
        val fileName = buildString {
            append(UUID.randomUUID().toString())
            append(name)
            append(".png")
        }
        saveImageFromUseCase(
            url = url,
            filename = fileName
        )
        return fileName
    }
}