plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.serialization")
}

group = "com.danilkha"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.logging)
    implementation(libs.apache.commons.io)
    implementation(libs.jwt.parser)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlin.reflect)

    implementation(project(":Api"))
    implementation(project(":Domain"))


    implementation(libs.springfox.swagger2){
        exclude("io.swagger", "swagger-annotations")
        exclude("io.swagger", "swagger-models")
    }
    implementation(libs.springfox.swagger2.models)
    implementation(libs.springfox.swagger2.annotations)
    implementation(libs.springfox.swagger2.ui)
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