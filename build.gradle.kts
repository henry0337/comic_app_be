import java.io.FileInputStream
import java.util.*

plugins {
    java
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.dependency.management.spring)
    alias(libs.plugins.sentry.jvm.gradle)
}

group = "com.henry"
version = "1.0.1"

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
    implementation(libs.spring.data.jpa)
    implementation(libs.spring.security)
    implementation(libs.spring.web)
    implementation(libs.spring.oauth2.resource)
    implementation(libs.spring.mail)
    developmentOnly(libs.spring.devtools)
//    developmentOnly(libs.docker.compose)

    // MySQL Driver
    runtimeOnly(libs.mysql)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // JJWT
    implementation(libs.jjwt.api)
    implementation(libs.jjwt.jackson)
    implementation(libs.jjwt.impl)

    // Apache Commons Lang
    implementation(libs.apache.commons.lang)

    // Swagger
    implementation(libs.swagger)

    // MapStruct
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)

    testImplementation(libs.boot.test)
    testImplementation(libs.security.test)

    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.compileJava {
    options.compilerArgs.addAll(
        mutableListOf(
            "-Amapstruct.suppressGeneratorTimestamp=true",
            "-Amapstruct.suppressGeneratorVersionInfoComment=true",
            "-Amapstruct.verbose=true"
        )
    )
}