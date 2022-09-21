package com.server.somnium

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableJpaAuditing
class SomniumApplication {
	@PostConstruct
	fun applicationTimeZoneSetter() {

		val timeZone = TimeZone.getTimeZone("Asia/Seoul")
		TimeZone.setDefault(timeZone)
	}
}

fun main(args: Array<String>) {
	runApplication<SomniumApplication>(*args)
}
