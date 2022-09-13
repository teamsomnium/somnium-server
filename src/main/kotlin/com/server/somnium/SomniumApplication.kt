package com.server.somnium

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SomniumApplication

fun main(args: Array<String>) {
	runApplication<SomniumApplication>(*args)
}
