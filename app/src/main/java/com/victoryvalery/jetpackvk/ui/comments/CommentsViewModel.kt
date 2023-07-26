package com.victoryvalery.jetpackvk.ui.comments

import androidx.lifecycle.ViewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.PostComment
import com.victoryvalery.jetpackvk.ui.comments.CommentsScreenState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommentsViewModel(
    feedPost: FeedPostItem
) : ViewModel(){

    private val _commentsState = MutableStateFlow<CommentsScreenState>(Initial)
    val commentsState = _commentsState.asStateFlow()

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPostItem: FeedPostItem) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(it))
            }
        }
        _commentsState.value = Comments(
            feedPost = feedPostItem,
            comments = comments
        )
    }

}