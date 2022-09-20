package com.server.somnium

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SomniumApplication

fun main(args: Array<String>) {
	runApplication<SomniumApplication>(*args)
}
