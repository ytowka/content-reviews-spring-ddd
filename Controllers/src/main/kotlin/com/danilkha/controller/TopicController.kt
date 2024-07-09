package com.danilkha.controller

import com.danilkha.contentreviews.api.theme.ThemeApi
import com.danilkha.contentreviews.api.theme.ThemeResponse
import com.danilkha.domain.usecase.topic.GetAllTopicsUseCase
import com.danilkha.mappers.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/topics")
class TopicController(
    private val getAllTopicsUseCase: GetAllTopicsUseCase
) : ThemeApi{

    @GetMapping
    override fun getAllThemes(): List<ThemeResponse> {
        return getAllTopicsUseCase().map { it.toResponse() }
    }
}