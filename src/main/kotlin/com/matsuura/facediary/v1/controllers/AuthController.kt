package com.matsuura.facediary.v1.controllers

import com.matsuura.facediary.extensions.isEmailValidate
import com.matsuura.facediary.extensions.isPasswordValidate
import com.matsuura.facediary.utils.Constants
import com.matsuura.facediary.utils.ErrorCode
import com.matsuura.facediary.utils.JwtTokenUtils
import com.matsuura.facediary.utils.Utils
import com.matsuura.facediary.v1.models.request.CreateUserRequest
import com.matsuura.facediary.v1.models.request.VerifyRequest
import com.matsuura.facediary.v1.services.SendEmailService
import com.matsuura.facediary.v1.services.auth.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var sendEmailService: SendEmailService

    @GetMapping("/login")
    fun login(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String,
    ): Map<String, Any> {

        // validation check
        if (!email.isEmailValidate() || !password.isPasswordValidate()) {
            val status: String = Constants.FAILURE
            val errorCode: String = ErrorCode.ES00_001
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        // login
        val response: Map<String, Any> = authService.login(
            email = email,
            password = password
        )

        if (response["status"] as String == Constants.FAILURE) {
            val status: String = Constants.FAILURE
            val errorCode: String = response["errorCode"] as String
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        // create access token
        val accessToken = JwtTokenUtils.createJwtToken(email = email)

        return mapOf(
            "status" to Constants.SUCCESS,
            "errorCode" to "",
            "accessToken" to accessToken,
        )

    }

    @PostMapping("/createUser")
    fun createUser(
        @RequestBody user: CreateUserRequest
    ): Map<String, Any> {

        val email: String = user.email
        val password: String = user.password

        // validation check
        if (!email.isEmailValidate() || !password.isPasswordValidate()) {
            val status: String = Constants.FAILURE
            val errorCode: String = ErrorCode.ES00_001
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        val token: String = Utils.createUniqueToken(
            email = email,
            password = password
        )

        // create user
        val response: Map<String, Any> = authService.createUser(
            email = email,
            password = password,
            token = token,
        )

        if (response["status"] as String == Constants.FAILURE) {
            val status: String = Constants.FAILURE
            val errorCode: String = response["errorCode"] as String
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        // send email
        sendEmailService.sendEmail(
            from = Constants.MAIL_ACCOUNT,
            to = email,
            title = Constants.REGISTER_MAIL_TITLE,
            message = Constants.REGISTER_MAIL_MESSAGE + "http://localhost:8080/facediary?token=${token}",
        )

        return mapOf(
            "status" to Constants.SUCCESS,
            "errorCode" to "",
        )

    }

    @PostMapping("/verify")
    fun verify (@RequestBody user: VerifyRequest): Map<String, Any> {

        val email: String = user.email
        val token: String = user.token

        // validation check
        if (!email.isEmailValidate()) {
            val status: String = Constants.FAILURE
            val errorCode: String = ErrorCode.ES00_001
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        val response = authService.verify(token = token)

        if (response["status"] as String == Constants.FAILURE) {
            val status: String = Constants.FAILURE
            val errorCode: String = response["errorCode"] as String
            return mapOf(
                "status" to status,
                "errorCode" to errorCode
            )
        }

        // create access token
        val accessToken = JwtTokenUtils.createJwtToken(email = email)

        return mapOf(
            "status" to Constants.SUCCESS,
            "errorCode" to "",
            "accessToken" to accessToken,
        )

    }

}