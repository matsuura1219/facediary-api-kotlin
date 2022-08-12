package com.matsuura.facediary.utils

object ErrorCode {

    // バリデーションエラー
    const val ES00_001: String = "ES00_001"

    // パスワードが不一致の場合
    const val ES01_001: String = "ES01_001"
    // ユーザが存在しない場合
    const val ES01_002: String = "ES01_002"
    // メールアドレス未検証の場合
    const val ES01_003: String = "ES01_003"

    // ユーザが存在している場合
    const val ES02_001: String = "ES02_001"

    // トークンが存在しない場合
    const val ES04_001: String = "ES04_001"
}