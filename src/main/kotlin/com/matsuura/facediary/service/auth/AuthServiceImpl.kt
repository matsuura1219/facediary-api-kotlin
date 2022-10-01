package com.matsuura.facediary.service.auth

import com.matsuura.facediary.exception.*
import com.matsuura.facediary.model.User
import com.matsuura.facediary.model.dto.InsertUserDto
import com.matsuura.facediary.model.dto.UpdateVerifyTokenDto
import com.matsuura.facediary.repository.auth.AuthMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl: AuthService {

    @Autowired
    private lateinit var authMapper: AuthMapper

    override fun login(email: String, password: String) {

        val user: User = authMapper.findUserByEmail(email = email)
            ?: throw NotFoundException(
                code = "ES01_003",
                message = "The user does not exist",
            )

        if (!user.isVerified) {
            throw UnAuthorisedException(
                code = "ES01_004",
                message = "Email is not verified",
            )
        }

        if (user.password != password) {
            throw WrongPasswordException(
                code = "ES01_005",
                message = "Password is wrong",
            )
        }

    }

    @Transactional
    override fun createUser(email: String, password: String, token: String) {

        val user: User? = authMapper.findUserByEmail(email = email)
        if (user != null && user.isVerified) {
            throw AlreadyRegisteredException(
                code = "ES02_003",
                message = "User is already registerd",
            )
        }

        if (user == null) {
            val dto = InsertUserDto(
                email = email,
                password = password,
                token = token,
            )

            val insertCount = try {
                authMapper.insertUser(dto = dto)
            } catch (e: DuplicateKeyException) {
                throw DuplicateErrorException(
                    code = "ES02_004",
                    message = "Verify token is duplicated",
                )
            }

            if (insertCount != 1) {
                throw DbErrorException(
                    code = "ES02_005",
                    message = "Insert process is failure and rollback",
                )
            }

        } else {

            val dto = UpdateVerifyTokenDto(
                email = email,
                token = token,
            )

            val updateCount = try {
                authMapper.updateVerifyToken(dto = dto)
            } catch (e: DuplicateKeyException) {
                throw DuplicateErrorException(
                    code = "ES02_004",
                    message = "Verify token is duplicated",
                )
            }

            if (updateCount != 1) {
                throw DbErrorException(
                    code = "ES02_005",
                    message = "Update process is failure and rollback",
                )
            }
        }

    }

    @Transactional
    override fun verifyMailToken(token: String): User {

        val user: User = authMapper.findUserByVerifyToken(token = token)
            ?: throw VerifyTokenErrorException(
                code = "ES03_001",
                message = "Mail token is not validate",
            )

        val updateCount: Int = authMapper.updateVerifyFlag(token = token)
        if (updateCount != 1) {
            throw DbErrorException(
                code = "ES03_002" ,
                message = "Update process is failure and rollback",
            )
        }

        return user

    }

    @Transactional
    override fun resetPassword(email: String, passwordToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null || !user.isVerified) {
            throw NotFoundException(
                code = "ES04_002",
                message = "The user does not exist",
            )
        }

        val updateCount: Int = authMapper.updateResetPasswordToken(
            email = email,
            passwordToken = passwordToken,
        )

        if (updateCount != 1) {
            throw DbErrorException(
                code = "ES04_003",
                message = "Update process is failure and rollback",
            )
        }

    }

    @Transactional
    override fun changePassword(email: String, password: String, token: String) {

        val user: User = authMapper.findUserByEmail(email = email)
            ?: throw NotFoundException(
                code = "ES05_005",
                message = "The user does not exist",
            )

        if (user.resetPasswordToken != token) {
            throw VerifyTokenErrorException(
                code = "ES05_006",
                message = "Password token is not validate",
            )
        }

        val updateCount: Int = authMapper.updatePassword(
            email = email,
            password = password,
        )

        if (updateCount != 1) {
            throw DbErrorException(
                code = "ES05_008",
                message = "Update process is failure and rollback",
            )
        }
    }

}