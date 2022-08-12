package com.matsuura.facediary.v1.services

interface SendEmailService {

    fun sendEmail(
        from: String,
        to: String,
        title: String,
        message: String,
    )
}