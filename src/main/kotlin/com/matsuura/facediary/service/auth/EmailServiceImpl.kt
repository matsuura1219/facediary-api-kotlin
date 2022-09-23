package com.matsuura.facediary.service.auth

import com.matsuura.facediary.exception.*
import com.matsuura.facediary.model.User
import com.matsuura.facediary.model.dto.InsertUserDto
import com.matsuura.facediary.model.dto.UpdateVerifyTokenDto
import com.matsuura.facediary.repository.auth.AuthMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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