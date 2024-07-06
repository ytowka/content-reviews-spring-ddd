package com.danilkha.data.entity

import com.danilkha.domain.model.Content
import javax.persistence.*

@Entity
@Table(name = "content")
data class ContentEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val themeId: Long,
    val name: String,
    val image: String?,
)