package com.victoryvalery.jetpackvk.ui.comments

import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.PostComment

sealed class CommentsScreenState {
    object Initial : CommentsScreenState()
    data class Comments(val feedPost: FeedPostItem, val comments: List<PostComment>) : CommentsScreenState()
}
