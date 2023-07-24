package com.victoryvalery.jetpackvk.domain

import com.victoryvalery.jetpackvk.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.base_avatar,
    val commentText: String = "Base comment text",
    val publicationDate: String = "14:00"
)
