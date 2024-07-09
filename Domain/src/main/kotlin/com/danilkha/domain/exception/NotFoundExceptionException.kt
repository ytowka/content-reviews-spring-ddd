package com.danilkha.domain.exception


open class NotFoundExceptionException(
    val domain: String?,
    val id: String?
): ServiceException(404, "$domain with id: $id not found") {
}