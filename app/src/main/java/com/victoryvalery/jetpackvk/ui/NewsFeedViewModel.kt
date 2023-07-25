package com.victoryvalery.jetpackvk.ui

import androidx.lifecycle.ViewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.StatisticsItem
import com.victoryvalery.jetpackvk.ui.NewsFeedScreenState.Posts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsFeedViewModel : ViewModel() {


    private val initList = mutableListOf<FeedPostItem>().apply {
        repeat(8) {
            add(FeedPostItem(publicationId = it))
        }
    }
    private val initState = Posts(initList)

    private val _screenState = MutableStateFlow<NewsFeedScreenState>(initState)
    val screenState = _screenState.asStateFlow()

    fun dismissElement(feedPostItem: FeedPostItem) {
        val currentState = screenState.value
        if (currentState is Posts) {
            _screenState.value = Posts(currentState.posts.toMutableList().apply { remove(feedPostItem) })
        }
    }

    fun updateCount(feedPostItem: FeedPostItem, item: StatisticsItem) {
        val currentState = screenState.value
        if (currentState is Posts) {
            _screenState.value = Posts(currentState.posts.toMutableList().apply {
                replaceAll {
                    if (it == feedPostItem) {
                        val oldStatistics = feedPostItem.publicationStatistics
                        val newStatistics = oldStatistics.toMutableList().apply {
                            apply {
                                replaceAll { oldStatisticsItem ->
                                    if (oldStatisticsItem.type == item.type)
                                        oldStatisticsItem.copy(count = oldStatisticsItem.count + 1)
                                    else
                                        oldStatisticsItem
                                }
                            }
                        }
                        it.copy(publicationStatistics = newStatistics)
                    } else
                        it
                }
            })
        }
    }
}