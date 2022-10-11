package com.server.somnium.global.jwt.exception.config

import com.server.somnium.global.jwt.exception.filter.ExceptionHandlerFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.access.channel.ChannelProcessingFilter

@Configuration
class ExceptionHandlerFilterConfig(
    private val exceptionHandlerFilter: ExceptionHandlerFilter
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity?) {
        httpSecurity!!.addFilterAfter(exceptionHandlerFilter, ChannelProcessingFilter::class.java)
    }
}