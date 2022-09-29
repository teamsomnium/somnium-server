package com.server.somnium.global.exception

import com.server.somnium.global.exception.response.ExceptionResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(
        e: IllegalAccessException
    ): ResponseEntity<ExceptionResponseEntity> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(ExceptionResponseEntity.of(e))
    }

}