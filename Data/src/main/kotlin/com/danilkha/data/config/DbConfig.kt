package com.danilkha.data.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.danilkha.data.jparepository"],
)
@EntityScan(basePackages = ["com.danilkha.data"])
class DbConfig