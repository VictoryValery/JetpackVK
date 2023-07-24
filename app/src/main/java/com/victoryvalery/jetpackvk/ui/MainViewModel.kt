package com.victoryvalery.jetpackvk.ui

import androidx.lifecycle.ViewModel
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.domain.StatisticsItem
import com.victoryvalery.jetpackvk.ui.HomeScreenState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {



    private val initList = mutableListOf<FeedPostItem>().apply {
        repeat(8) {
            add(FeedPostItem(publicationId = it))
        }
    }

    private val _screenState = MutableStateFlow<HomeScreenState>(Posts(initList))
    val screenState = _screenState.asStateFlow()

    fun dismissElement(feedPostItem: FeedPostItem) {
        _screenState.value = _screenState.value.toMutableList().apply { remove(feedPostItem) }
    }

    fun updateCount(feedPostItem: FeedPostItem, item: StatisticsItem) {
        _screenState.value = _screenState.value.toMutableList().apply {
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
        }
    }
}