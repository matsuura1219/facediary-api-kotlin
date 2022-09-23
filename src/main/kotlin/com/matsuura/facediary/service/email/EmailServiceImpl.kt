package com.matsuura.facediary.service.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl: EmailService {

    @Autowired
    lateinit var mailSender: MailSender

    override fun sendEmail(
        from: String,
        to: String,
        title: String,
        message: String,
    ) {

        val msg: SimpleMailMessage = SimpleMailMessage()
        msg.setFrom(from)
        msg.setTo(to)
        msg.setSubject(title)
        msg.setText(message)

        try {
            mailSender.send(msg)
        } catch (e: MailException) {
            e.printStackTrace()
        }

    }
}