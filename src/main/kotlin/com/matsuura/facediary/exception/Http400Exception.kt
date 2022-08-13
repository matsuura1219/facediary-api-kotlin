package com.matsuura.facediary.exception

/**
 * 401エラー
 * @author matsuurayuki
 */

class Http400Exception constructor(
    message: String,
) : RuntimeException(message)