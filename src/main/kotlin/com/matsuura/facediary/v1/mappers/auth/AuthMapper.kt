package com.matsuura.facediary.v1.mappers.auth

import com.matsuura.facediary.v1.models.UserModel
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AuthMapper {

    fun findByEmail(email: String): UserModel?

}