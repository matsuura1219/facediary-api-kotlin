package com.matsuura.facediary.repository.auth

import com.matsuura.facediary.model.User
import com.matsuura.facediary.model.dto.InsertUserDto
import com.matsuura.facediary.model.dto.UpdateVerifyTokenDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AuthMapper {

    fun findUserByEmail(email: String): User?

    fun findUserByVerifyToken(verifyToken: String): User?

    fun insertUser(dto: InsertUserDto): Int

    fun updateVerifyToken(dto: UpdateVerifyTokenDto): Int

    fun updateVerifyFlag(verifyToken: String): Int

    fun updateResetPasswordToken(email: String, passwordToken: String): Int

    fun updatePassword(email: String, password: String): Int

}