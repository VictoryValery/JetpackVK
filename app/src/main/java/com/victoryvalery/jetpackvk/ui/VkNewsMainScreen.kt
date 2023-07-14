package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victoryvalery.jetpackvk.ui.NavigationItem.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VkNewsMainScreen(viewModel: MainViewModel) {

    val feedPost = viewModel.feedPost.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(72.dp)
            ) {
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf(Home, Favourite, Profile)
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.titleResId)) },
                        label = { Text(stringResource(item.titleResId)) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                    )
                }
            }
        }
    )
    {
        PostCard(
            modifier = Modifier.padding(it),
            feedPostItem = feedPost.value,
            onCommentItemClickListener = viewModel::updateCount,
            onLikeItemClickListener = viewModel::updateCount,
            onShareItemClickListener = viewModel::updateCount,
            onViewsItemClickListener = viewModel::updateCount,
        )
    }
}