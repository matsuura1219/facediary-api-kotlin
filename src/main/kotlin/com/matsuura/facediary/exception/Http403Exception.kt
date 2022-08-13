package com.matsuura.facediary.exception

/**
 * 404エラー
 * @author matsuurayuki
 */

class Http403Exception constructor(
    message: String,
) : RuntimeException(message)