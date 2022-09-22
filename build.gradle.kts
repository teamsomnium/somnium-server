import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("plugin.noarg") version "1.6.21"
	kotlin("plugin.allopen") version "1.6.21"
	jacoco
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

group = "com.server"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

	finalizedBy("jacocoTestReport")
}

jacoco {
	toolVersion = "0.8.8"
}

tasks.jacocoTestReport {
	reports {
		// 원하는 리포트를 켜고 끌 수 있다.
		html.isEnabled = true
		xml.isEnabled = false
		csv.isEnabled = false
	}

	finalizedBy("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			// element가 없으면 프로젝트의 전체 파일을 합친 값을 기준으로 한다.
			limit {
				counter = "INSTRUCTION"
				value = "COVEREDRATIO"
				minimum = "0.00".toBigDecimal()
			}
		}

		rule {
			// 룰을 간단히 켜고 끌 수 있다.
			enabled = true

			// 룰을 체크할 단위는 클래스 단위
			element = "CLASS"

			//초기 설정으로 BRANCH 커버리지가 0퍼센트여도 빌드에 성공하게 설정
			limit {
				counter = "BRANCH"
				value = "COVEREDRATIO"
				minimum = "0.00".toBigDecimal()
			}

			//초기 설정으로 LINE 커버리지가 0퍼센트여도 빌드에 성공하게 설정
			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = "0.00".toBigDecimal()
			}

			// 빈 줄을 제외한 코드의 라인수를 최대 200라인으로 제한한다.
			limit {
				counter = "LINE"
				value = "TOTALCOUNT"
				maximum = "200".toBigDecimal()
			}

			// 커버리지 체크를 제외할 클래스들
			excludes = listOf(
			)
		}
	}
}

val testCoverage by tasks.registering {
	group = "verification"
	description = "Runs the unit tests with coverage"

	dependsOn(":test",
			":jacocoTestReport",
			":jacocoTestCoverageVerification")

	tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
	tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}