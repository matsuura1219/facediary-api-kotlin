package com.matsuura.facediary.v1.models.requests

data class CreateUserRequest(
    val email: String,
    val password: String,
)