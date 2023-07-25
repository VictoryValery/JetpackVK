package com.victoryvalery.jetpackvk.ui

import com.victoryvalery.jetpackvk.domain.FeedPostItem

sealed class NewsFeedScreenState {
    object Initial : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPostItem>) : NewsFeedScreenState()
}
