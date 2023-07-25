package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.navigation.AppNavGraph
import com.victoryvalery.jetpackvk.navigation.rememberNavigationState
import com.victoryvalery.jetpackvk.ui.NavigationItem.Favourite
import com.victoryvalery.jetpackvk.ui.NavigationItem.Home
import com.victoryvalery.jetpackvk.ui.NavigationItem.Profile

@Composable
fun VkNewsMainScreen() {

    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPostItem?> = remember {
        mutableStateOf(null)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(72.dp)
            ) {
                //стейт хранения текущего открытого экрана - currentBackStackEntryAsState()
                //если будет открыт другой экран, то и стейт изменится
                // и произойдёт рекомпозиция необходимых composable функций
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                //название того экрана, который сейчас открыт
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(Home, Favourite, Profile)
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.titleResId)) },
                        label = { Text(stringResource(item.titleResId)) },
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navigationState.navigateTo(item.screen.route)
                        },
                    )
                }
            }
        }
    )
    {
        AppNavGraph(
            navController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null)
                    HomeScreen(
                        paddingValues = it,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                else
                    CommentsScreen {
                        commentsToPost.value = null
                    }
            },
            favouriteScreenContent = { OtherScreen(name = "Favourite") },
            profileScreenContent = { OtherScreen(name = "Profile") }
        )
    }
}

@Composable
fun OtherScreen(name: String) {
    val count = rememberSaveable {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable(enabled = true) {
            count.value++
        },
        text = "$name count: ${count.value}"
    )
}