package com.matsuura.facediary.exception

class NotFoundException(
    val code: String,
    message: String,
) : RuntimeException(message)