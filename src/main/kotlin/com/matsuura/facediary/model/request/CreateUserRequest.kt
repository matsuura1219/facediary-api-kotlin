package com.matsuura.facediary.model.request

data class CreateUserRequest(
    val email: String,
    val password: String,
)