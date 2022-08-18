package com.matsuura.facediary.v1.mappers.auth

import com.matsuura.facediary.v1.dto.InsertUserDto
import com.matsuura.facediary.v1.dto.UpdateVerifyFlagDto
import com.matsuura.facediary.v1.dto.UpdateVerifyTokenDto
import com.matsuura.facediary.v1.models.User
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