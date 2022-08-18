package com.matsuura.facediary.v1.services.email

interface EmailService {

    fun sendEmail(
        from: String,
        to: String,
        title: String,
        message: String,
    )
}