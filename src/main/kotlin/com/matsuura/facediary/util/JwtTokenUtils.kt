package com.matsuura.facediary.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.matsuura.facediary.exception.Http401Exception
import java.util.*


object JwtTokenUtils {

    private const val EXPIRATION_TIME: Long = 1000L * 60L * 10L
    private const val SECRET_KEY: String = "facediaryapp"

    fun generateJwtToken(email: String): String {

        val secretKey: String = SECRET_KEY
        val issuedAt: Date = Date()
        val notBefore: Date = Date(issuedAt.time)
        val expiresAt: Date = Date(issuedAt.time + EXPIRATION_TIME)

        val algorithm: Algorithm = Algorithm.HMAC256(secretKey)
        val token = JWT.create()
            .withJWTId("jwtId")
            .withAudience("audience")
            .withIssuer("issuer")
            .withSubject(email)
            .withIssuedAt(issuedAt)
            .withNotBefore(notBefore)
            .withExpiresAt(expiresAt)
            .withClaim("X-AUTHORITIES", "aaa")
            .withClaim("X-USERNAME", "bbb")
            .sign(algorithm)

        return token

    }

    fun verify (token: String): String {

        val algorithm: Algorithm = Algorithm.HMAC256(SECRET_KEY)
        val verifier: JWTVerifier = JWT.require(algorithm).build()

        val jwt = try {
            verifier.verify(token)
        } catch (e: JWTVerificationException) {
            throw Http401Exception(
                message = "Token Expired",
                errorCode = "ES00_000",
            )
        }

        // registered claims
        val subject = jwt.subject
        val issuedAt = jwt.issuedAt
        val notBefore = jwt.notBefore
        val expiresAt = jwt.expiresAt
        val authorities = jwt.getClaim("X-AUTHORITIES").asString()
        val username = jwt.getClaim("X-USERNAME").asString()

        return subject

    }
}