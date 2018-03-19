import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.2.21"
	id("org.springframework.boot") version "2.0.0.RELEASE"
	id("org.jetbrains.kotlin.jvm") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
	id("io.spring.dependency-management") version "1.0.4.RELEASE"
	jacoco
	id("com.github.kt3k.coveralls") version "2.8.1"
}

version = "0.0.1-SNAPSHOT"

jacoco {

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

tasks.withType<JacocoReport> {
	reports {
		html.isEnabled = true
		xml.isEnabled = true
	}
}

repositories {
	mavenCentral()
}

dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compile("org.jetbrains.kotlin:kotlin-reflect")
	compile("com.fasterxml.jackson.module:jackson-module-kotlin")
	testCompile("org.springframework.boot:spring-boot-starter-test")
}
