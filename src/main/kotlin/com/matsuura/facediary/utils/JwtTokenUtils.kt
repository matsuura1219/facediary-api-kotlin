package com.matsuura.facediary.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm.HMAC256
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import java.util.*

object JwtTokenUtils {

    fun createJwtToken(email: String): String {

        val algorithm: com.auth0.jwt.algorithms.Algorithm = HMAC256("private_key")
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.SECOND, 30)
        val expireTime = calendar.time

        val token = try {
            JWT.create()
                .withIssuer("auth0")
                .withClaim("name", email)
                .withExpiresAt(expireTime)
                .sign(algorithm)
        } catch (e: JWTCreationException) {
            ""
        }

        return token
    }

    fun authenticateToken(token: String): Boolean {
        val algorithm: com.auth0.jwt.algorithms.Algorithm = HMAC256("private_key")
        val verifier: JWTVerifier = JWT.require(algorithm)
            .withIssuer("auth0")
            .build()

        val isValid: Boolean =
            try {
                verifier.verify(token)
                true
            } catch (e: JWTVerificationException) {
                false
            }

        return isValid
    }

}