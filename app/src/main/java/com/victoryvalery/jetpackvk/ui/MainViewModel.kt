package com.victoryvalery.jetpackvk.ui

import androidx.lifecycle.ViewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.StatisticsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _feedPost = MutableStateFlow(FeedPostItem())
    val feedPost = _feedPost.asStateFlow()

    fun updateCount(item: StatisticsItem) {
        val oldStatistics = feedPost.value.publicationStatistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldStatisticsItem ->
                if (oldStatisticsItem.type == item.type)
                    oldStatisticsItem.copy(count = oldStatisticsItem.count + 1)
                else
                    oldStatisticsItem
            }
        }
        _feedPost.value = feedPost.value.copy(publicationStatistics = newStatistics)
    }

}