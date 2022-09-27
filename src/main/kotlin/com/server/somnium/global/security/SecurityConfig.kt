package com.server.somnium.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring()
                    .antMatchers("/h2-console/**")
                    .antMatchers("/swagger-ui/index.html")
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .cors().and()
                .csrf().and()
                .httpBasic().disable()

                .authorizeHttpRequests {
                    it.anyRequest().permitAll()
                }
                .build()
    }

}