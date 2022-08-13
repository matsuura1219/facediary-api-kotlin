package com.matsuura.facediary.exception

/**
 * 401エラー
 * @author matsuurayuki
 */

class Http400Exception constructor(
    message: String,
    errorCode: String,
) : RuntimeException(message) {

    val errorCode: String

    init {
        this.errorCode = errorCode
    }
}