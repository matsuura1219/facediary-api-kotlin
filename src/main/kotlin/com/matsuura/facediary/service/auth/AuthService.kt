package com.matsuura.facediary.service.auth

import com.matsuura.facediary.model.User

interface AuthService {

    fun login(email: String, password: String)

    fun createUser(email: String, password: String, verifyToken: String)

    fun verifyMailToken(token: String): User

    fun resetPassword(email: String, passwordToken: String)

    fun changePassword(email: String, password: String, token: String)

}