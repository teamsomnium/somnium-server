package com.server.somnium.global.security

import com.server.somnium.global.jwt.exception.config.ExceptionHandlerFilterConfig
import com.server.somnium.global.jwt.exception.exception.JwtAccessDeniedHandler
import com.server.somnium.global.jwt.exception.exception.JwtAuthenticationEntryPoint
import com.server.somnium.global.jwt.exception.filter.ExceptionHandlerFilter
import com.server.somnium.global.jwt.filter.JwtTokenFilter
import com.server.somnium.global.jwt.filter.config.JwtTokenFilterConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtTokenFilter: JwtTokenFilter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .cors().disable()
                .csrf().disable()
                .httpBasic().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeHttpRequests {
                    // HttpSecure Ignore Path
                    it.antMatchers("/h2-console/**", "/swagger-ui/index.html").permitAll()

                    it.anyRequest().permitAll()
                }

                .exceptionHandling() {
                    it.accessDeniedHandler(jwtAccessDeniedHandler)
                    it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                }


                .apply(JwtTokenFilterConfig(jwtTokenFilter)).and()
                .apply(ExceptionHandlerFilterConfig(exceptionHandlerFilter)).and()

                .build()
    }

}