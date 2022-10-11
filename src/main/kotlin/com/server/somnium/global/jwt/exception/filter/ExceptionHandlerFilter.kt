package com.server.somnium.global.jwt.exception.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.somnium.global.exception.response.ExceptionResponseEntity
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            setResponseEntity(HttpStatus.UNAUTHORIZED, response, e)
        }
    }

    private fun <T: Throwable> setResponseEntity(
        httpStatus: HttpStatus,
        response: HttpServletResponse,
        exception: T
    ) {
        response.status = httpStatus.value()
        val responseEntity = objectMapper.writeValueAsString(ExceptionResponseEntity.of(exception))
        response.writer.write(responseEntity)
    }
}