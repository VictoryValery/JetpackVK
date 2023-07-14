package com.victoryvalery.jetpackvk.domain

import com.victoryvalery.jetpackvk.R

data class FeedPostItem(
    val authorName: String = "authorName test",
    val authorAvatarId: Int = R.drawable.base_avatar,
    val publicationDate: String = "14:42",
    val publicationId: Int = 0,
    val publicationText: String = "Simple publication text",
    val publicationImageId: Int = R.drawable.fresh_start,
    val publicationStatistics: List<StatisticsItem> = listOf(
        StatisticsItem(StatisticsType.LIKES, 10),
        StatisticsItem(StatisticsType.SHARES, 20),
        StatisticsItem(StatisticsType.COMMENTS, 520),
        StatisticsItem(StatisticsType.VIEWS, 420)
    )
)
