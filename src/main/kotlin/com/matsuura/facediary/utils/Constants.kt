package com.matsuura.facediary.utils

object Constants {

    const val SUCCESS: String = "0"
    const val FAILURE: String = "1"

    const val EMAIL_PATTERNS: String = "[a-zA-Z0-9._-]+@[a-z]{2,}+\\\\.+[a-z]{2,}"
    const val PASSWORD_PATTERNS: String = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    const val PASSWORD_MAX_LENGTH: Int = 8

}