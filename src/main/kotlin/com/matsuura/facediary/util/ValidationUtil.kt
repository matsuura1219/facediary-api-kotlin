package com.matsuura.facediary.util

object ValidationUtil {

    fun checkEmailValidate(email: String): Boolean {
        val isEmpty: Boolean = email.isEmpty()
        if (isEmpty) return false
        return true
    }

    fun checkPasswordValidate(password: String): Boolean {
        val isEmpty: Boolean = password.isEmpty()
        if (isEmpty) return false
        return true
    }
}