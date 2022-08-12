package com.matsuura.facediary.v1.models.request

data class CreateUserRequest (
    val email: String,
    val password: String,
)