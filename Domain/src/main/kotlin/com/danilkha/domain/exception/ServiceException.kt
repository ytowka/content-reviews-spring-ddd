package com.danilkha.domain.exception


open class ServiceException(
    val httpStatus: Int,
    override val message: String?
): Exception(message){
}