package com.victoryvalery.jetpackvk.ui

import androidx.lifecycle.ViewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.PostComment
import com.victoryvalery.jetpackvk.ui.CommentsScreenState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommentsViewModel : ViewModel() {

    private val _commentsState = MutableStateFlow<CommentsScreenState>(Initial)
    val commentsState = _commentsState.asStateFlow()

    init {
        loadComments(FeedPostItem())
    }

    fun loadComments(feedPostItem: FeedPostItem) {
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