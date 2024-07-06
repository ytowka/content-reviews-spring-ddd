package com.danilkha.domain.exception


class AuthenticationException : ServiceException(
    httpStatus = 401,
    message = "Authentication failed",
)