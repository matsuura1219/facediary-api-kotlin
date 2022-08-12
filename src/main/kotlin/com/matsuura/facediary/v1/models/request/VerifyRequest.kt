package com.matsuura.facediary.v1.models.request

data class VerifyRequest(
    val email: String,
    val token: String,
)
