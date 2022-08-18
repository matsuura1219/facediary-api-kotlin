package com.matsuura.facediary.v1.models.requests

data class ChangePasswordRequest(
    val email: String,
    val oldPassword: String,
    val newPassword: String,
    val passwordToken: String,
)