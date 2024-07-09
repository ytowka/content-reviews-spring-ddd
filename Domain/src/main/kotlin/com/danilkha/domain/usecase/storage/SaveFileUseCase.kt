package com.danilkha.domain.usecase.storage

import com.danilkha.domain.repository.StorageRepository
import java.io.File
import java.io.InputStream
import javax.annotation.ManagedBean

@ManagedBean
class SaveFileUseCase(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(
        fileName: String,
        inputStream: InputStream,
        size: Long
    ): String{
        return storageRepository.saveFile(fileName, inputStream, size)
    }
}