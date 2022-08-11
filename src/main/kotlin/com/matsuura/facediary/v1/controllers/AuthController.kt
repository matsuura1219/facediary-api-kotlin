package com.matsuura.facediary.v1.controllers

import com.matsuura.facediary.extensions.isEmailValidate
import com.matsuura.facediary.extensions.isPasswordValidate
import com.matsuura.facediary.utils.Constants
import com.matsuura.facediary.utils.ErrorCode
import com.matsuura.facediary.utils.JwtTokenUtils
import com.matsuura.facediary.v1.services.auth.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

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

}