package com.matsuura.facediary.exception

import com.matsuura.facediary.util.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Http400Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun http400Handler(e: Http400Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

    @ExceptionHandler(Http401Exception::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun http401Handler(e: Http401Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

    @ExceptionHandler(Http404Exception::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun http404Handler(e: Http404Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

    @ExceptionHandler(Http500Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun http500Handler(e: Http404Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = e.message ?: throw Exception("error message is blank!!")
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

    @ExceptionHandler(SQLException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun sqlExceptionHandler(e: Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = ErrorMessage.DB_ERROR
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun otherExceptionHandler(e: Exception): Map<String, Any> {

        e.printStackTrace()

        val message: String = ErrorMessage.UNKNOWN_ERROR
        val errorCode: String = ErrorMessage.CODE_LIST[message] ?: throw Exception("error code is not defined!!")

        return mapOf (
            "message" to message,
            "errorCode" to errorCode,
        )
    }

}