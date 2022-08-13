package com.matsuura.facediary.exception

/**
 * 404エラー
 * @author matsuurayuki
 */

class Http404Exception constructor(
    message: String,
) : RuntimeException(message)