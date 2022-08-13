package com.matsuura.facediary.exception

/**
 * 500エラー
 * @author matsuurayuki
 */

class Http500Exception constructor(
    message: String,
) : RuntimeException(message)