package com.danilkha.domain.exception

class UserNotFoundException(
    username: String
) : ServiceException(
    httpStatus = 404,
    message = "user with login $username not found"
)