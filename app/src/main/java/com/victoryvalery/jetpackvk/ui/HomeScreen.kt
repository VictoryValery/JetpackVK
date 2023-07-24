package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.ui.HomeScreenState.*

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val screenState = viewModel.screenState.collectAsState()
    when (val currentState = screenState.value) {
        is Posts -> {
            FeedPosts(paddingValues = paddingValues, viewModel = viewModel, feed = currentState.posts)
        }

        is Comments -> {
            CommentsScreen(feedPost = currentState.feedPost, comments = currentState.comments)
        }

        Initial -> {
            LinearProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    feed: List<FeedPostItem>
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = feed, key = { item: FeedPostItem -> item.publicationId }) { feedPostItem: FeedPostItem ->
            val dismissState = rememberDismissState(
                positionalThreshold = { 200.dp.toPx() }
            )
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart))
                viewModel.dismissElement(feedPostItem)
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                background = {},
                dismissContent = {
                    PostCard(
                        feedPostItem = feedPostItem,
                        onCommentItemClickListener = { statisticsItem ->
                            viewModel.updateCount(
                                feedPostItem,
                                statisticsItem
                            )
                        },
                        onLikeItemClickListener = { statisticsItem -> viewModel.updateCount(feedPostItem, statisticsItem) },
                        onShareItemClickListener = { statisticsItem -> viewModel.updateCount(feedPostItem, statisticsItem) },
                        onViewsItemClickListener = { statisticsItem -> viewModel.updateCount(feedPostItem, statisticsItem) },
                    )
                }
            )
        }
    }
}