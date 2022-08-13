package com.matsuura.facediary.v1.services.auth

import com.matsuura.facediary.exception.Http400Exception
import com.matsuura.facediary.exception.Http401Exception
import com.matsuura.facediary.exception.Http404Exception
import com.matsuura.facediary.exception.Http500Exception
import com.matsuura.facediary.main
import com.matsuura.facediary.util.Constants
import com.matsuura.facediary.util.ErrorCode
import com.matsuura.facediary.v1.dto.InsertUserDto
import com.matsuura.facediary.v1.dto.UpdateVerifyFlagDto
import com.matsuura.facediary.v1.mappers.auth.AuthMapper
import com.matsuura.facediary.v1.models.User
import org.apache.ibatis.javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
class AuthServiceImpl: AuthService {

    @Autowired
    private lateinit var authMapper: AuthMapper

    override fun login(email: String, password: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null) {
            throw Http404Exception(
                message = "Not Exist User",
                errorCode = "ES00_000",
            )
        }

        if (!user.isVerified) {
            throw Http400Exception(
                message = "Unccorect Verify Token",
                errorCode = "ES00_000",
            )
        }

        if (user.password != password) {
            throw Http401Exception(
                message = "Uncorrect Password",
                errorCode = "ES00_000",
            )
        }

    }

    @Transactional
    override fun createUser(email: String, password: String, verifyToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)
        if (user != null) {
            throw Http400Exception(
                message = "Registered User",
                errorCode = "ES00_000",
            )
        }

        val dto: InsertUserDto = InsertUserDto(
            email = email,
            password = password,
            verifyToken = verifyToken,
        )

        val insertCount = try {
            authMapper.insertUser(dto = dto)
        } catch (e: DuplicateKeyException) {
            throw Http500Exception(
                message = "Duplicate Key",
                errorCode = "ES00_001"
            )
        }

        if (insertCount != 1) {
            throw Http500Exception(
                message = "DB Error",
                errorCode = "ES00_001"
            )
        }
    }

    @Transactional
    override fun verifyToken(verifyToken: String): User {

        val user: User? = authMapper.findUserByVerifyToken(verifyToken = verifyToken)

        if (user == null) {
            throw Http404Exception(
                message = "Not Exist User",
                errorCode = "ES00_000",
            )
        }

        val updateCount: Int = authMapper.updateVerifyFlag(verifyToken = verifyToken)
        if (updateCount != 1) {
            throw Http500Exception(
                message = "DB Error",
                errorCode = "ES00_000",
            )
        }

        return user

    }

    @Transactional
    override fun resetPassword(email: String, passwordToken: String) {

        val user: User? = authMapper.findUserByEmail(email = email)

        if (user == null) {
            throw Http404Exception(
                message = "Not Exist User",
                errorCode = "ES00_000",
            )
        }

        val updateCount: Int = authMapper.updateResetPasswordToken(
            email = email,
            passwordToken = passwordToken,
        )

        if (updateCount != 1) {
            throw Http500Exception(
                message = "DB Error",
                errorCode = "ES00_000"
            )
        }

    }

}