package com.matsuura.facediary.exception

class DuplicateErrorException(
    val code: String,
    message: String,
) : RuntimeException(message)