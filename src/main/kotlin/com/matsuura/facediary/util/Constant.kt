package com.matsuura.facediary.util

object Constant {

    const val MAIL_ACCOUNT: String = "facediary1234@gmail.com"

    const val REGISTER_MAIL_URL: String = "https://facediaryapp.page.link/?link=https://facediaryapp.com/register?token=%s&apn=jp.matsuura.facediary"

    const val REGISTER_MAIL_TITLE: String = "【FaceDiary】登録手続きをお願いします"
    const val REGISTER_MAIL_MESSAGE: String =
        "この度はFaceDiaryへのユーザ登録ありがとうございました。\r\n" +
                "以下のリンクを押下し、アプリの登録を完了をしてください。\r\n" +
                "%s"

    const val RESET_PASSWORD_MAIL_URL: String = "https://facediaryapp/reset?token=%s"

    const val RESET_PASSWORD_MAIL_TITLE: String = "【FaceDiary】パスワード変更の手続きをお願いします"
    const val RESET_PASSWORD_MAIL_MESSAGE: String =
        "パスワードの変更を行ってください。\r\n" +
                "以下のリンクを押下し、パスワードの変更が可能です。\r\n" +
                "%s"

}