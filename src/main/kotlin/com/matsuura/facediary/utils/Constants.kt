package com.matsuura.facediary.utils

object Constants {

    const val SUCCESS: String = "0"
    const val FAILURE: String = "1"

    const val EMAIL_PATTERNS: String = "[a-zA-Z0-9._-]+@[a-z]{2,}+\\\\.+[a-z]{2,}"
    const val PASSWORD_PATTERNS: String = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    const val PASSWORD_MAX_LENGTH: Int = 8

    const val MAIL_ACCOUNT: String = "facediary1234@gmail.com"

    const val REGISTER_MAIL_TITLE: String = "【FaceDiary】登録手続きをお願いします"
    const val REGISTER_MAIL_MESSAGE: String =
        "この度はFaceDiaryへのユーザ登録ありがとうございました。\r\n" +
                "以下のリンクを押下し、アプリの登録を完了をしてください。\r\n"

}