package com.matsuura.facediary.exception

/**
 * 401エラー
 * @author matsuurayuki
 */

class Http401Exception constructor(
    message: String,
) : RuntimeException(message)