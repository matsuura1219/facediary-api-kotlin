package com.matsuura.facediary.v1.dto

data class InsertUserDto (
    val email: String,
    val password: String,
    val verifyToken: String,
)