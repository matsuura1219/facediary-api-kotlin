package com.matsuura.facediary.exception

class WrongPasswordException(
    val code: String,
    message: String,
) : RuntimeException(message)