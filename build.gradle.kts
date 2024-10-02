import java.io.FileInputStream
import java.util.*

plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("io.sentry.jvm.gradle") version "4.11.0"
}

group = "com.henry"
version = "0.0.1-SNAPSHOT"

val localProperties = Properties().apply {
	load(FileInputStream(rootProject.file("local.properties")))
}

val sentryAuthToken: String = localProperties.getProperty("SENTRY_AUTH_TOKEN")
	?: throw GradleException("SENTRY_AUTH_TOKEN not found in local.properties")

sentry {
	includeSourceContext = true

	org = "henry-qi"
	projectName = "bounty-hunter"
	authToken = sentryAuthToken
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-rest") // Không nên dùng nếu bạn muốn cấu hình controller thủ công.
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// MySQL Driver
	runtimeOnly("com.mysql:mysql-connector-j")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// JJWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")

	// Apache Commons Lang
	implementation("org.apache.commons:commons-lang3:3.17.0")

	// Swagger UI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
