package com.server.somnium.global.exception.response

class ExceptionResponseEntity(
    val message: String?
) {
    companion object {
        fun <T: Throwable> of(exception: T): ExceptionResponseEntity {
            return ExceptionResponseEntity(exception.message)
        }
    }
}