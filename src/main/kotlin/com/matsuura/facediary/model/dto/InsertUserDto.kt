package com.matsuura.facediary.model.dto

data class InsertUserDto (
    val email: String,
    val password: String,
    val verifyToken: String,
)