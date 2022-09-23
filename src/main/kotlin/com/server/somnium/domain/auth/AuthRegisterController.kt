package com.server.somnium.domain.auth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/oauth/register")
class AuthRegisterController {

    @GetMapping
    fun register(
        @RequestParam
        authorizeCode: String
    ) {

    }
}