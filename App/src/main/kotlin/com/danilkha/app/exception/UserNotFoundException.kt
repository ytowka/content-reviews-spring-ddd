package com.danilkha.app.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(
    username: String
) : ServiceException(
    httpStatus = HttpStatus.NOT_FOUND,
    message = "user with login $username not found"
)