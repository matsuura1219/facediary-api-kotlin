package com.matsuura.facediary.service.email

interface EmailService {

    fun sendEmail(
        from: String,
        to: String,
        title: String,
        message: String,
    )

}