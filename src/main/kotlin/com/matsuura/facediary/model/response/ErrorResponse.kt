package com.matsuura.facediary.model.response

data class ErrorResponse(
    val errorCode: String,
    val message: String,
)