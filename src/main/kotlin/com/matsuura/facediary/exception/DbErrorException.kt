package com.matsuura.facediary.exception

class DbErrorException(
    val code: String,
    message: String,
) : RuntimeException(message)