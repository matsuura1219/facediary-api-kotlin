package com.matsuura.facediary.model.request

data class VerifyEmailTokenRequest(
    val verifyToken: String,
)