package com.server.somnium.domain.auth.property

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class AuthProperties {

    @Value("\${auth.client-id}")
    lateinit var clientId: String
    @Value("\${auth.redirect-uri}")
    lateinit var redirectUri: String
    @Value("\${auth.client-secret}")
    lateinit var clientSecret: String
}

