package com.danilkha.domain.exception



class UserAlreadyRegisteredException : ServiceException(
    httpStatus = 409,
    message = "User Already Registered",
)