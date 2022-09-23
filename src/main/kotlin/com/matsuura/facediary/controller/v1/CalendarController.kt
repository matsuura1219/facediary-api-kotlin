package com.matsuura.facediary.controller.v1

import com.matsuura.facediary.exception.ValidationErrorException
import com.matsuura.facediary.model.response.AuthResponse
import com.matsuura.facediary.model.response.SuccessResponse
import com.matsuura.facediary.util.JwtTokenUtil
import com.matsuura.facediary.util.ValidationUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * カレンダーの処理を行うコントローラクラスです
 */
@RestController
@RequestMapping("/v1/calendar")
class CalendarController {

    /** 1ヶ月ごとのデータを取得します */
    @GetMapping("/get_month_data")
    fun getDataPerMonth(): SuccessResponse {
        return SuccessResponse(message = "OK")
    }
}