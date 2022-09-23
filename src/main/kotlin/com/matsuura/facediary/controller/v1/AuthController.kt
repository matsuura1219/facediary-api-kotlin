package com.matsuura.facediary.controller.v1

import com.matsuura.facediary.exception.ValidationErrorException
import com.matsuura.facediary.model.response.AuthResponse
import com.matsuura.facediary.model.response.SuccessResponse
import com.matsuura.facediary.model.User
import com.matsuura.facediary.model.request.ChangePasswordRequest
import com.matsuura.facediary.model.request.CreateUserRequest
import com.matsuura.facediary.service.auth.AuthService
import com.matsuura.facediary.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 認証周りの処理を行うコントローラクラスです
 */
@RestController
@RequestMapping("/v1/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    /** ログイン可否を判定します */
    @GetMapping("/login")
    fun login(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String,
    ): AuthResponse {

        if (ValidationUtil.checkEmailValidate(email = email)) {
            throw ValidationErrorException(
                code = "ES01_001",
                message = "Email is not validate",
            )
        }

        if (ValidationUtil.checkPasswordValidate(password = password)) {
            throw ValidationErrorException(
                code = "ES01_002",
                message = "Password is not validate",
            )
        }

        authService.login(email = email, password = password)

        val accessToken: String = JwtTokenUtil.generateJwtToken(email = email)

        return AuthResponse(accessToken = accessToken)

    }

    /** ユーザ登録を行います */
    @PostMapping("/create_user")
    fun createUser(
        @RequestBody request: CreateUserRequest
    ) : SuccessResponse {

        val email: String = request.email
        val password: String = request.password

        if (ValidationUtil.checkEmailValidate(email = email)) {
            throw ValidationErrorException(
                code = "ES02_001",
                message = "Email is not validate",
            )
        }

        if (ValidationUtil.checkPasswordValidate(password = password)) {
            throw ValidationErrorException(
                code = "ES02_002",
                message = "Password is not validate",
            )
        }

        val verifyToken: String = CommonUtils.generateUniqueToken(
            email = email,
            password = password,
        )

        authService.createUser(
            email = email,
            password = password,
            verifyToken = verifyToken,
        )

        // TODO: SNSでメール送信したい

        return SuccessResponse(message = "OK")

    }


    /** ユーザ登録用トークンのチェックを行います */
    @GetMapping("/verify_email_token")
    fun verifyEmailToken(
        @RequestParam("verifyToken") verifyToken: String,
    ) : AuthResponse {

        val user: User = authService.verifyMailToken(verifyToken = verifyToken)

        val accessToken: String = JwtTokenUtil.generateJwtToken(email = user.email)

        return AuthResponse(accessToken = accessToken)
    }

    /** パスワードリセットを行います */
    @GetMapping("/reset_password")
    fun resetPassword (
        @RequestParam("email") email: String,
    ): SuccessResponse {

        if (ValidationUtil.checkEmailValidate(email = email)) {
            throw ValidationErrorException(
                code = "ES04_001",
                message = "Email is not validate",
            )
        }

        val passwordToken: String = CommonUtils.generateUniqueToken(
            email = email,
        )

        authService.resetPassword(email = email, passwordToken = passwordToken)

        // TODO: SNSで送信したい

        return SuccessResponse(message = "OK")
    }

    /** パスワード変更を行います */
    @PostMapping("/change_password")
    fun changePassword(
        @RequestBody request: ChangePasswordRequest
    ): SuccessResponse {

        val email: String = request.email
        val oldPassword: String = request.oldPassword
        val newPassword: String = request.newPassword
        val passwordToken: String = request.passwordToken

        if (ValidationUtil.checkEmailValidate(email = email)) {
            throw ValidationErrorException(
                code = "ES05_001",
                message = "Email is not validate",
            )
        }

        if (ValidationUtil.checkPasswordValidate(password = oldPassword)) {
            throw ValidationErrorException(
                code = "ES05_002",
                message = "Old password is not validate",
            )
        }

        if (ValidationUtil.checkPasswordValidate(password = newPassword)) {
            throw ValidationErrorException(
                code = "ES05_003",
                message = "New password is not validate",
            )
        }

        if (oldPassword == newPassword) {
            throw ValidationErrorException(
                code = "ES05_004",
                message = "New password is not validate",
            )
        }

        authService.changePassword(
            email = email,
            oldPassword = oldPassword,
            newPassword = newPassword,
            passwordToken = passwordToken,
        )

        return SuccessResponse(message = "OK")
    }

}