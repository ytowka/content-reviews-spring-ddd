package com.danilkha.data.repository

import com.danilkha.domain.repository.StorageRepository
import io.minio.*
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL
import java.util.*

@Component
class StorageRepositoryImpl(
    private val minioClient: MinioClient
) : StorageRepository {

    override fun saveFile(
        fileName: String,
        inputStream: InputStream,
        size: Long
    ): String {
        try {
            val bucketExists = minioClient.bucketExists(
                BucketExistsArgs
                    .builder()
                    .bucket(FILES_BUCKET)
                    .build()
            )
            if (!bucketExists) {
                minioClient.makeBucket(
                    MakeBucketArgs.builder()
                        .bucket(FILES_BUCKET)
                        .build()
                )
            }
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(FILES_BUCKET)
                    .`object`(fileName)
                    .stream(inputStream, size, -1)
                    .build()
            )
            return fileName
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun saveImageFrom(url: URL, filename: String): String {
        val inputStream = BufferedInputStream(url.openStream())
        val temp = File(UUID.randomUUID().toString()+".tmp")
        FileOutputStream(temp).use { out ->
            out.write(inputStream.readAllBytes())
        }

        saveFile(
            fileName = filename,
            inputStream = temp.inputStream(),
            size = temp.length(),
        )
        temp.delete()
        return filename
    }


    override fun getFile(filename: String): InputStream {
        return minioClient.getObject(
            GetObjectArgs
            .builder()
            .bucket(FILES_BUCKET)
            .`object`(filename)
            .build()
        )
    }

    companion object{
        private const val FILES_BUCKET = "files"
    }
}