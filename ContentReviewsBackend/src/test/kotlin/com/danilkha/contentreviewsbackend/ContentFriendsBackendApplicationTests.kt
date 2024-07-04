package com.danilkha.contentreviewsbackend

import com.danilkha.contentreviewsbackend.repository.ContentRepository
import com.danilkha.contentreviewsbackend.repository.TopicRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContentReviewsBackendApplicationTests {

    @Autowired
    lateinit var contentRepository: ContentRepository

    @Autowired
    lateinit var topicRepository: TopicRepository

    @Test
    fun `avg mark and count subquery works`() {
        assertDoesNotThrow {
           val result = contentRepository.getContentWithReviews(1,0, 10)
            println("size: ${result.size}")
            result.forEach {
                println("result: ${it.name} (${it.themeId}), ${it.avg} ${it.count}")
            }
        }
    }

    @Test
    fun `content count formula works`(){
        assertDoesNotThrow {
            val result = topicRepository.findAll().firstOrNull()
            if(result != null) {
                println(result.contentCount)
            }else{
                println("no topic found")
            }
        }
    }

}
