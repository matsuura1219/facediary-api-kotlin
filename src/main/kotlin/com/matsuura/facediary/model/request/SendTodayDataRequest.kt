package com.matsuura.facediary.model.request

import java.io.File

data class SendTodayDataRequest(
    val image: File,
    val text: String,
    val adequacy: Int,
    val anger: Double,
    val contempt: Double,
    val disgust: Double,
    val fear: Double,
    val happiness: Double,
    val neutral: Double,
    val sadness: Double,
    val surprise: Double,
)