package com.server.somnium.global.jwt.filter

import com.server.somnium.global.jwt.JwtTokenProvider
import io.jsonwebtoken.lang.Strings
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = jwtTokenProvider.getToken(request)
        token ?: filterChain.doFilter(request, response)

        if (jwtTokenProvider.validateToken(token!!)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }


}