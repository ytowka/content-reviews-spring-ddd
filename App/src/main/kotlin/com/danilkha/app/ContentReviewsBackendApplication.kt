package com.danilkha.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [
    "com.danilkha.domain",
    "com.danilkha.data",
    "com.danilkha.app",
    "com.danilkha.controller",
])
class ContentReviewsBackendApplication

fun main(args: Array<String>) {
    runApplication<ContentReviewsBackendApplication>(*args)
}
