package com.danilkha.app.exception

import com.danilkha.contentreviews.ExceptionResponse
import com.danilkha.domain.exception.ServiceException
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(
    val logger: Logger
) {

    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(exception: ServiceException): ResponseEntity<ExceptionResponse> {
        logger.error("${exception.httpStatus} code with ${exception.message}")
        exception.printStackTrace()
        return ResponseEntity.status(exception.httpStatus)
            .body(ExceptionResponse(
                status = exception.httpStatus,
                error = exception::class.simpleName!!,
                message = exception.message ?: ""
            ))
    }

    @ExceptionHandler(Exception::class)
    fun handleServiceException(exception: Exception): ResponseEntity<ExceptionResponse> {
        logger.error("${400} code with ${exception.message}")
        exception.printStackTrace()
        return ResponseEntity.status(400)
            .body(ExceptionResponse(
                status = 400,
                error = exception::class.simpleName!!,
                message = exception.message ?: ""
            ))
    }
}