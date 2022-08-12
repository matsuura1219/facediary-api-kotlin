package com.matsuura.facediary.utils

import java.security.MessageDigest

object Utils {

    fun createUniqueToken(email: String, password: String): String {
        val uniqueStr: String = email + password
        val strHash = MessageDigest.getInstance("SHA-256")
            .digest(uniqueStr.toByteArray())
            .joinToString(separator = "") {
                "%02x".format(it)
            }
        return strHash
    }
}