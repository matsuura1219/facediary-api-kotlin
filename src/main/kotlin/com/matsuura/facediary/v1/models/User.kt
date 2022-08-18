package com.matsuura.facediary.v1.models

data class User(
    val email: String,
    val password: String,
    val isVerified: Boolean,
    val verifyToken: String?,
    val resetPasswordToken: String?,
)
