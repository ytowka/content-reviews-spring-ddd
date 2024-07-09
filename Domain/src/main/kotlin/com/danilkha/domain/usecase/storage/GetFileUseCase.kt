package com.danilkha.domain.usecase.storage

import com.danilkha.domain.repository.StorageRepository
import java.io.File
import java.io.InputStream
import javax.annotation.ManagedBean

@ManagedBean
class GetFileUseCase(
    private val storageRepository: StorageRepository
) {

    operator fun invoke(fileName: String): InputStream {
        return storageRepository.getFile(fileName)
    }
}