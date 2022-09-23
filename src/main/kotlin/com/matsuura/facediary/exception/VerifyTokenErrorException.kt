package com.matsuura.facediary.exception

class VerifyTokenErrorException(
    val code: String,
    message: String,
) : RuntimeException(message)