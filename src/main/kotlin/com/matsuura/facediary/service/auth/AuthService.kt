package com.matsuura.facediary.service.auth

import com.matsuura.facediary.model.User

interface AuthService {

    fun login(email: String, password: String)

    fun createUser(email: String, password: String, verifyToken: String)

    fun verifyMailToken(verifyToken: String): User

    fun resetPassword(email: String, passwordToken: String)

    fun changePassword(email: String, oldPassword: String, newPassword: String, passwordToken: String)

}