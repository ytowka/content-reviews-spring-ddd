package com.danilkha.data.entity

import com.danilkha.domain.model.Topic
import org.hibernate.annotations.Formula
import javax.persistence.*


@Entity
@Table(name = "topic")
data class TopicEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val imageUrl: String,
){
    @Formula("(select count(*) from content c where c.theme_id = id)")
    val contentCount: Int = 0
}

fun TopicEntity.toDto(): Topic = Topic(
    id = id,
    name = name,
    contentCount = contentCount,
    imageUrl = imageUrl
)