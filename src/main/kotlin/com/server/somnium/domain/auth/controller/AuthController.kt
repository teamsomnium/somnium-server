package com.server.somnium.domain.auth.controller

import com.server.somnium.domain.auth.service.AuthRegistrationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/oauth/register")
class AuthController(
    private val authRegistrationService: AuthRegistrationService
) {

    @GetMapping
    fun register(
        @RequestParam
        code: String
    ) {
        authRegistrationService.register(code)
    }
}