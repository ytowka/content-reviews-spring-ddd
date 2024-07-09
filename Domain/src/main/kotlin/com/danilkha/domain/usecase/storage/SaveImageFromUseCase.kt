package com.danilkha.domain.usecase.storage

import com.danilkha.domain.repository.StorageRepository
import java.net.URL
import javax.annotation.ManagedBean

@ManagedBean
class SaveImageFromUseCase(
    private val storageRepository: StorageRepository
) {

    operator fun invoke(url: URL, filename: String): String{
        return storageRepository.saveImageFrom(url, filename)
    }
}