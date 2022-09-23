package com.matsuura.facediary.exception

class UnAuthorisedException(
    val code: String,
    message: String,
) : RuntimeException(message)