package com.matsuura.facediary.v1.services.auth

import com.matsuura.facediary.utils.Constants
import com.matsuura.facediary.utils.ErrorCode
import com.matsuura.facediary.v1.mappers.auth.AuthMapper
import com.matsuura.facediary.v1.models.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sun.jvm.hotspot.utilities.ConstantTag

@Service
class AuthServiceImpl: AuthService {

    @Autowired
    private lateinit var authMapper: AuthMapper

    override fun login(email: String, password: String): Map<String, Any> {

        val user: UserModel? = authMapper.findByEmail(email = email)

        if (user == null) {
            val status: String = Constants.FAILURE
            val errorCode: String = ErrorCode.ES01_002
            return mapOf(
                "status" to status,
                "errorCode" to errorCode,
            )
        }

        if (user.password != password) {
            val status: String = Constants.FAILURE
            val errorCode: String = ErrorCode.ES01_001
            return mapOf(
                "status" to status,
                "errorCode" to errorCode,
            )
        }

        return mapOf(
            "status" to Constants.SUCCESS,
            "errorCode" to "",
        )

    }


}