package com.matsuura.facediary.exception

/**
 * 500エラー
 * @author matsuurayuki
 */

class Http500Exception constructor(
    message: String,
    errorCode: String,
) : RuntimeException(message) {

    val errorCode: String

    init {
        this.errorCode = errorCode
    }
}