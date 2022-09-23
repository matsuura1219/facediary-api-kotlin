package com.matsuura.facediary.model.request

data class ChangePasswordRequest(
    val email: String,
    val oldPassword: String,
    val newPassword: String,
    val passwordToken: String,
)