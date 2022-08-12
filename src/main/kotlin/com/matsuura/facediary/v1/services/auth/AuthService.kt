package com.matsuura.facediary.v1.services.auth

interface AuthService {

    fun login(email: String, password: String): Map<String, Any>

    fun createUser(email: String, password: String, token: String): Map<String, Any>

}