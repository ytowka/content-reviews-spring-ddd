plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    kotlin("plugin.spring") version "1.9.23"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("plugin.serialization")
}

group = "com.danilkha"
version = "unspecified"

dependencies {
    implementation(project(":Domain"))

    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.data)
    implementation(libs.spring.boot.starter.jdbc)
    implementation(libs.hibernate.core)
    implementation(libs.jwt.parser)
    implementation(libs.minio)
    implementation(libs.apache.commons.io)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization)
    runtimeOnly(libs.postges)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
}