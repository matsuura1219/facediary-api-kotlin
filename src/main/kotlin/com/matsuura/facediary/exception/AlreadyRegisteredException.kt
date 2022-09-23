package com.matsuura.facediary.exception

class AlreadyRegisteredException(
    val code: String,
    message: String,
) : RuntimeException(message)