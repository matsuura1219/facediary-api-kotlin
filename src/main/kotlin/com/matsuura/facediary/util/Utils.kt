package com.matsuura.facediary.util

import java.security.MessageDigest
import java.time.LocalDateTime

object Utils {

    fun generateUniqueToken(email: String, password: String = ""): String {
        val currentDateStr: String = LocalDateTime.now().toString()
        val uniqueStr: String = email + password + currentDateStr
        val strHash = MessageDigest.getInstance("SHA-256")
            .digest(uniqueStr.toByteArray())
            .joinToString(separator = "") {
                "%02x".format(it)
            }
        return strHash
    }
}