package com.matsuura.facediary.extension

import com.matsuura.facediary.util.Constants

fun String.isEmailValidate(): Boolean {
    val isEmpty: Boolean = this.isEmpty()
    if (isEmpty) return false
    // return this.matches(Regex(Constants.EMAIL_PATTERNS))
    return true
}

fun String.isPasswordValidate(): Boolean {
    val isEmpty: Boolean = this.isEmpty()
    if (isEmpty) return false
    // return this.length >= Constants.PASSWORD_MAX_LENGTH && this.matches(Regex(Constants.PASSWORD_PATTERNS))
    return this.length >= Constants.PASSWORD_MAX_LENGTH
}