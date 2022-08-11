package com.matsuura.facediary.v1.services.auth

import com.matsuura.facediary.v1.models.UserModel

interface AuthService {

    fun login(email: String, password: String): Map<String, Any>

}