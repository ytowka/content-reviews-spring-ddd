package com.danilkha.contentreviewsbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

class UserNotFoundException(
    username: String
) : ServiceException(
    httpStatus = HttpStatus.NOT_FOUND,
    message = "user with login $username not found"
)