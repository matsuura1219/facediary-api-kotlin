package com.matsuura.facediary.exception

class ValidationErrorException(
    val code: String,
    message: String,
) : RuntimeException(message)