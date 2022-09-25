package com.matsuura.facediary.model.dto

data class UpdateVerifyTokenDto(
    val email: String,
    val token: String,
)
