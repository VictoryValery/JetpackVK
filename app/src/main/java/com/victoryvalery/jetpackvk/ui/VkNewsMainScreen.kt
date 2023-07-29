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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.victoryvalery.jetpackvk.domain.FeedPostItem
import com.victoryvalery.jetpackvk.navigation.AppNavGraph
import com.victoryvalery.jetpackvk.navigation.NavigationItem.Favourite
import com.victoryvalery.jetpackvk.navigation.NavigationItem.Home
import com.victoryvalery.jetpackvk.navigation.NavigationItem.Profile
import com.victoryvalery.jetpackvk.navigation.rememberNavigationState
import com.victoryvalery.jetpackvk.ui.comments.CommentsScreen

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
                val items = listOf(Home, Favourite, Profile)
                items.forEach { item ->
                    //чтобы понять находимся ли мы внутри какого-то раздела меню,
                    // мы проверяем всю вложенную иерархию экранов
                    // и ищем в ерархии элемент чей route совадает с элементом навигации
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.titleResId)) },
                        label = { Text(stringResource(item.titleResId)) },
                        selected = selected,
                        onClick = {
                            if (!selected)
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
            newsFeedScreenContent = {
                if (commentsToPost.value == null)
                    HomeScreen(
                        paddingValues = it,
                        onCommentClickListener = {
                            commentsToPost.value = it
                            // осуществляем переход по вложенному графу навигации
                            // navigationState.navigateTo(Screen.Comments.route)
                            navigationState.navigateToComments()
                        }
                    )
            },
            favouriteScreenContent = { OtherScreen(name = "Favourite") },
            profileScreenContent = { OtherScreen(name = "Profile") },
            commentsScreenContent = {
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = commentsToPost.value!!
                )
            }
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