package com.server.somnium.global.jwt.exception.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.somnium.global.exception.response.ExceptionResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        response!!.status = HttpServletResponse.SC_UNAUTHORIZED
        val responseEntity = objectMapper.writeValueAsString(ExceptionResponseEntity.of(authException!!))
        response.writer.write(responseEntity)
    }
}