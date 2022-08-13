package com.matsuura.facediary.util

object ErrorMessage {

    const val VALIDATION_ERROR: String = "VALIDATION_ERROR"

    const val DUPLICATE_KEY: String = "DUPLICATE_KEY"

    const val DB_ERROR: String = "DB_ERROR"

    const val TOKEN_EXPIRED: String = "TOKEN_EXPIRED"

    const val NOT_USER_EXIST: String = "NOT_USER_EXIST"

    const val PASSWORD_ERROR: String = "PASSWORD_ERROR"

    const val MAIL_NOT_VERIFIED: String = "MAIL_NOT_VERIFIED"

    const val USER_ALREADY_EXISTED: String = "USER_ALREADY_EXISTED"

    const val VERIFY_TOKEN_ERROR: String = "VERIFY_TOKEN_ERROR"

    const val UNKNOWN_ERROR: String = "UNKNOWN_ERROR"

    val CODE_LIST: Map<String, String> = mapOf(

        // common
        VALIDATION_ERROR to "ES00_001",
        DUPLICATE_KEY to "ES00_002",
        DB_ERROR to "ES00_003",
        TOKEN_EXPIRED to "ES00_004",

        // authentication
        NOT_USER_EXIST to "ES01_001",
        PASSWORD_ERROR to "ES01_002",
        MAIL_NOT_VERIFIED to "ES01_003",
        USER_ALREADY_EXISTED to "ES01_004",
        VERIFY_TOKEN_ERROR to "ES01_005",

        // other
        UNKNOWN_ERROR to "ES99_001",
    )

}