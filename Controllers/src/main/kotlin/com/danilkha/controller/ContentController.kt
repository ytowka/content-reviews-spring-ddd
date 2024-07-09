package com.danilkha.controller

import com.danilkha.contentreviews.api.content.ContentApi
import com.danilkha.contentreviews.api.content.ContentListResponse
import com.danilkha.contentreviews.api.content.ContentResponse
import com.danilkha.domain.usecase.topic.GetAllContentUseCase
import com.danilkha.domain.usecase.topic.GetContentByIdUseCase
import com.danilkha.domain.usecase.topic.SearchContentUseCase
import com.danilkha.mappers.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/content")
class ContentController(
    private val getAllContentUseCase: GetAllContentUseCase,
    private val searchContentUseCase: SearchContentUseCase,
    private val getContentByIdUseCase: GetContentByIdUseCase,
) : ContentApi{

    @GetMapping(params = ["id", "page"])
    override fun getAllContents(@RequestParam("id") themeId: Long, @RequestParam("page") page: Int): ContentListResponse {
        return getAllContentUseCase(themeId, page).toResponse()
    }

    @GetMapping(params = ["id", "q"])
    override fun search(@RequestParam("id") themeId: Long, @RequestParam("q") query: String): List<ContentResponse> {
        return searchContentUseCase(themeId, query).map { it.toResponse() }
    }

    @GetMapping("/{contentId}")
    override fun getById(@PathVariable("contentId") contentId: Long): ContentResponse {
        return getContentByIdUseCase(contentId).toResponse()
    }
}
