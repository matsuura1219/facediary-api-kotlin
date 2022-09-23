package com.matsuura.facediary.service.auth

interface EmailService {

    fun sendEmail(
        from: String,
        to: String,
        title: String,
        message: String,
    )

}