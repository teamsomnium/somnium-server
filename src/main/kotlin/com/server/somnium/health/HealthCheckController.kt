package com.server.somnium.health

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping
    fun healthCheckController(): ResponseEntity<String> {
        return ResponseEntity.ok()
                .body("Somnium Server Health Check Success.")
    }
}