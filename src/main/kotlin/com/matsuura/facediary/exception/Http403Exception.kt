package com.matsuura.facediary.exception

/**
 * 404エラー
 * @author matsuurayuki
 */

class Http403Exception constructor(
    message: String,
    errorCode: String,
) : RuntimeException(message) {

    val errorCode: String

    init {
        this.errorCode = errorCode
    }
}