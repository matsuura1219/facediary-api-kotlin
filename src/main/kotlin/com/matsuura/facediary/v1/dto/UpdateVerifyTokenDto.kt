package com.matsuura.facediary.v1.dto

data class UpdateVerifyTokenDto(
    val email: String,
    val verifyToken: String,
)
