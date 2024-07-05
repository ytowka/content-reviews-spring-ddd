package com.danilkha.app.exception

import org.springframework.http.HttpStatus

class UserAlreadyRegisteredException : ServiceException(
    httpStatus = HttpStatus.CONFLICT,
    message = "User Already Registered",
)