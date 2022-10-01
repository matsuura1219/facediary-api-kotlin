package com.matsuura.facediary.model.request

data class ChangePasswordRequest(
    val email: String,
    val password: String,
    val token: String,
)