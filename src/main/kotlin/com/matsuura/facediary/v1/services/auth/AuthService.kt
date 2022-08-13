package com.matsuura.facediary.v1.services.auth

import com.matsuura.facediary.v1.models.User

interface AuthService {

    fun login(email: String, password: String)

    fun createUser(email: String, password: String, verifyToken: String)

    fun verifyToken(verifyToken: String): User

    fun resetPassword(email: String, passwordToken: String)

}