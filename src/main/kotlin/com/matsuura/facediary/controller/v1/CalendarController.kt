package com.matsuura.facediary.controller.v1

import com.matsuura.facediary.exception.ValidationErrorException
import com.matsuura.facediary.model.request.ChangePasswordRequest
import com.matsuura.facediary.model.request.SendTodayDataRequest
import com.matsuura.facediary.model.response.AuthResponse
import com.matsuura.facediary.model.response.SuccessResponse
import com.matsuura.facediary.util.JwtTokenUtil
import com.matsuura.facediary.util.ValidationUtil
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/send_today_data")
    fun sendTodayData(
        @RequestBody request: SendTodayDataRequest,
    ): SuccessResponse {
        return SuccessResponse(message = "OK")
    }
}