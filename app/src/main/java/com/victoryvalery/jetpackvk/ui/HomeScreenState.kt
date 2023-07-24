package com.victoryvalery.jetpackvk.ui

import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.PostComment

sealed class HomeScreenState {
    object Initial: HomeScreenState()
    data class Posts(val posts: List<FeedPostItem>) : HomeScreenState()
    data class Comments(val feedPost: FeedPostItem, val comments: List<PostComment>) : HomeScreenState()
}
