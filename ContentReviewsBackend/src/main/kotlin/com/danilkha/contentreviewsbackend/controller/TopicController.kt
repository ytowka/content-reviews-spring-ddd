package com.danilkha.contentreviewsbackend.controller

import com.danilkha.contentreviews.api.theme.ThemeApi
import com.danilkha.contentreviews.api.theme.ThemeResponse
import com.danilkha.contentreviewsbackend.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/topics")
class TopicController(
    private val topicService: TopicService
) : ThemeApi{

    @GetMapping
    override fun getAllThemes(): List<ThemeResponse> {
        return topicService.getTopics()
    }
}