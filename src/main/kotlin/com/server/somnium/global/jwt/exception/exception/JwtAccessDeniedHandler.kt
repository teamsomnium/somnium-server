package com.server.somnium.global.jwt.exception.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.somnium.global.exception.response.ExceptionResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAccessDeniedHandler(
    private val objectMapper: ObjectMapper
): AccessDeniedHandler {

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        response!!.status = HttpServletResponse.SC_FORBIDDEN
        val responseEntity = objectMapper.writeValueAsString(ExceptionResponseEntity.of(accessDeniedException!!))
        response.writer.write(responseEntity)
    }
}