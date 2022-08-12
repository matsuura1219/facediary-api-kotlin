package com.matsuura.facediary.v1.models

data class UserModel(
    val email: String,
    val password: String,
    val isVerified: Boolean,
)
