import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode

plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version "2.2.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(24))
	}
}
kotlin {
	compilerOptions {
		jvmDefault = JvmDefaultMode.ENABLE
	}
}
repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // bỏ duplicate
	implementation("org.postgresql:postgresql:42.7.7")
	implementation("org.flywaydb:flyway-core:11.10.2")
	implementation("org.flywaydb:flyway-database-postgresql:11.10.2")
	implementation("io.github.wimdeblauwe:htmx-spring-boot:4.0.1")
	implementation("org.webjars:font-awesome:6.4.0")
	implementation(kotlin("stdlib-jdk8"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

}


tasks.withType<Test> {
	useJUnitPlatform()
}
