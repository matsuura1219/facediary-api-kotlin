package com.matsuura.facediary.model

data class User(
    val email: String,
    val password: String,
    val isVerified: Boolean,
    val verifyToken: String?,
    val resetPasswordToken: String?,
)
