package com.danilkha.domain.repository

import java.io.InputStream
import java.net.URL

interface StorageRepository {

    fun saveFile(
        fileName: String,
        inputStream: InputStream,
        size: Long
    ): String

    fun saveImageFrom(url: URL, filename: String): String

    fun getFile(filename: String): InputStream
}