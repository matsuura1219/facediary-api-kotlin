package com.matsuura.facediary.v1.services.auth

import com.matsuura.facediary.exception.Http400Exception
import com.matsuura.facediary.exception.Http401Exception
import com.matsuura.facediary.exception.Http404Exception
import com.matsuura.facediary.exception.Http500Exception
import com.matsuura.facediary.util.ErrorMessage
import com.matsuura.facediary.v1.dto.InsertUserDto
import com.matsuura.facediary.v1.dto.UpdateVerifyTokenDto
import com.matsuura.facediary.v1.mappers.auth.AuthMapper
import com.matsuura.facediary.v1.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl: AuthService {

    @Autowired
    private lateinit var authMapper: AuthMapper

    override fun login(email: String, password: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null) {
            throw Http404Exception(
                message = ErrorMessage.NOT_USER_EXIST,
            )
        }

        if (!user.isVerified) {
            throw Http401Exception(
                message = ErrorMessage.MAIL_NOT_VERIFIED,
            )
        }

        if (user.password != password) {
            throw Http401Exception(
                message = ErrorMessage.PASSWORD_ERROR
            )
        }

    }

    @Transactional
    override fun createUser(email: String, password: String, verifyToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)
        if (user != null && user.isVerified) {
            throw Http400Exception(
                message = ErrorMessage.USER_ALREADY_EXISTED
            )
        }

        if (user == null) {

            val dto: InsertUserDto = InsertUserDto(
                email = email,
                password = password,
                verifyToken = verifyToken,
            )

            val insertCount = try {
                authMapper.insertUser(dto = dto)
            } catch (e: DuplicateKeyException) {
                throw Http500Exception(
                    message = ErrorMessage.DUPLICATE_KEY,
                )
            }

            if (insertCount != 1) {
                throw Http500Exception(
                    message = ErrorMessage.DB_ERROR
                )
            }

        } else {

            val dto: UpdateVerifyTokenDto = UpdateVerifyTokenDto(
                email = email,
                verifyToken = verifyToken,
            )

            val updateCount = try {
                authMapper.updateVerifyToken(dto = dto)
            } catch (e: DuplicateKeyException) {
                throw Http500Exception(
                    message = ErrorMessage.DUPLICATE_KEY,
                )
            }

            if (updateCount != 1) {
                throw Http500Exception(
                    message = ErrorMessage.DB_ERROR
                )
            }
        }

    }

    @Transactional
    override fun verifyToken(verifyToken: String): User {

        val user: User? = authMapper.findUserByVerifyToken(verifyToken = verifyToken)

        if (user == null) {
            throw Http401Exception(
                message = ErrorMessage.VERIFY_TOKEN_ERROR
            )
        }

        val updateCount: Int = authMapper.updateVerifyFlag(verifyToken = verifyToken)
        if (updateCount != 1) {
            throw Http500Exception(
                message = ErrorMessage.DB_ERROR,
            )
        }

        return user

    }

    @Transactional
    override fun resetPassword(email: String, passwordToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null || !user.isVerified) {
            throw Http404Exception(
                message = ErrorMessage.NOT_USER_EXIST
            )
        }

        val updateCount: Int = authMapper.updateResetPasswordToken(
            email = email,
            passwordToken = passwordToken,
        )

        if (updateCount != 1) {
            throw Http500Exception(
                message = ErrorMessage.DB_ERROR,
            )
        }

    }

    @Transactional
    override fun changePassword(email: String, oldPassword: String, newPassword: String, passwordToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null) {
            throw Http404Exception(
                message = ErrorMessage.NOT_USER_EXIST,
            )
        }

        if (user.resetPasswordToken != passwordToken) {
            throw Http401Exception(
                message = ErrorMessage.RESET_PASSWORD_TOKEN_MISTAKE,
            )
        }

        if (user.password != oldPassword) {
            throw Http401Exception(
                message = ErrorMessage.PASSWORD_ERROR,
            )
        }

        val updateCount: Int = authMapper.updatePassword(
            email = email,
            password = newPassword,
        )

        if (updateCount != 1) {
            throw Http500Exception(
                message = ErrorMessage.DB_ERROR,
            )
        }

    }

}