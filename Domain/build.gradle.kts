plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.danilkha"
version = "unspecified"

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.javax.annotation)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}