package com.matsuura.facediary.v1.controllers

import com.matsuura.facediary.exception.Http400Exception
import com.matsuura.facediary.extension.isEmailValidate
import com.matsuura.facediary.extension.isPasswordValidate
import com.matsuura.facediary.util.Constants
import com.matsuura.facediary.util.ErrorMessage
import com.matsuura.facediary.util.JwtTokenUtils
import com.matsuura.facediary.util.Utils
import com.matsuura.facediary.v1.models.User
import com.matsuura.facediary.v1.models.requests.CreateUserRequest
import com.matsuura.facediary.v1.models.requests.VerifyTokenRequest
import com.matsuura.facediary.v1.services.auth.AuthService
import com.matsuura.facediary.v1.services.email.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var emailService: EmailService

    @GetMapping("/login")
    fun login(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String,
    ): Map<String, Any> {

        // validation check
        if (!email.isEmailValidate() || !password.isPasswordValidate()) {
            throw Http400Exception(
                message = ErrorMessage.VALIDATION_ERROR,
            )
        }

        // login
        authService.login(
            email = email,
            password = password,
        )

        val accessToken: String = JwtTokenUtils.generateJwtToken(email = email)

        return mapOf(
            "message" to "Successful",
            "errorCode" to "",
            "accessToken" to accessToken,
        )

    }

    @PostMapping("/create_user")
    fun createUser(
        @RequestBody request: CreateUserRequest
    ) : Map<String, Any> {

        val email: String = request.email
        val password: String = request.password
        val verifyToken: String = Utils.generateVerifyToken(
            email = email,
            password = password,
        )

        // create user
        authService.createUser(
            email = email,
            password = password,
            verifyToken = verifyToken,
        )

        val url: String = String.format(Constants.REGISTER_MAIL_URL, verifyToken)

        // send email
        emailService.sendEmail(
            from = Constants.MAIL_ACCOUNT,
            to = email,
            title = Constants.REGISTER_MAIL_TITLE,
            message = String.format(Constants.REGISTER_MAIL_MESSAGE, url),
        )

        return mapOf(
            "message" to "Successful",
            "errorCode" to "",
        )

    }

    @PostMapping("/verify_token")
    fun verifyToken(
        @RequestBody request: VerifyTokenRequest
    ) : Map<String, Any> {

        val verifyToken: String = request.verifyToken

        // verify token
        val user: User = authService.verifyToken(verifyToken = verifyToken)

        val accessToken: String = JwtTokenUtils.generateJwtToken(email = user.email)

        return mapOf(
            "message" to "Successful",
            "errorCode" to "",
            "accessToken" to accessToken,
        )
    }

    @GetMapping("/reset_password")
    fun resetPassword (
        @RequestParam("email") email: String,
    ): Map<String, Any> {

        val passwordToken: String = Utils.generateVerifyToken(
            email = email,
        )

        authService.resetPassword(
            email = email,
            passwordToken = passwordToken,
        )

        val url: String = String.format(Constants.RESET_PASSWORD_MAIL_URL, passwordToken)
        emailService.sendEmail(
            from = Constants.MAIL_ACCOUNT,
            to = email,
            title = Constants.RESET_PASSWORD_MAIL_TITLE,
            message = String.format(Constants.RESET_PASSWORD_MAIL_MESSAGE, url),
        )

        return mapOf(
            "message" to "Successful",
            "errorCode" to "",
        )
    }

}