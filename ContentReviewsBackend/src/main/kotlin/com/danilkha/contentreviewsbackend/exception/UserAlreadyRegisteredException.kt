package com.danilkha.contentreviewsbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

class UserAlreadyRegisteredException : ServiceException(
    httpStatus = HttpStatus.CONFLICT,
    message = "User Already Registered",
)