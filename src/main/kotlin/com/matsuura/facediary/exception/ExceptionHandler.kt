package com.matsuura.facediary.exception

import com.matsuura.facediary.model.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(AlreadyRegisteredException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun alreadyRegisterExceptionHandler(e: AlreadyRegisteredException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(DbErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun dbErrorExceptionHandler(e: DbErrorException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(DuplicateErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun duplicateErrorExceptionHandler(e: DuplicateErrorException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(e: NotFoundException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(UnAuthorisedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun unAuthorisedException(e: UnAuthorisedException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(ValidationErrorException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationErrorException(e: ValidationErrorException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(VerifyTokenErrorException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun verifyTokenErrorException(e: VerifyTokenErrorException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(WrongPasswordException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun wrongPasswordException(e: WrongPasswordException): ErrorResponse {
        e.printStackTrace()
        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = e.code
        return ErrorResponse(
            message = message,
            errorCode = errorCode,
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unexpectedExceptionHandler(e: Exception): Map<String, Any> {
        e.printStackTrace()
        val message: String = "Unexpected error occurs"
        val errorCode: String = "ES99_099"
        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

}