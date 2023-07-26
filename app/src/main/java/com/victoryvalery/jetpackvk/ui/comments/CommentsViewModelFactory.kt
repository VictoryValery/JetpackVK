package com.victoryvalery.jetpackvk.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victoryvalery.jetpackvk.domain.FeedPostItem

class CommentsViewModelFactory(
    private val feedPost: FeedPostItem
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}