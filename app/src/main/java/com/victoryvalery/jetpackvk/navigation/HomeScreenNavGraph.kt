package com.victoryvalery.jetpackvk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
) {
    //вложенный граф навигации
    navigation(
        startDestination = Screen.NewsFeed.route, //указывает какой экран будет начальным
        route = Screen.Home.route// Имя вложенного графа навигации
    ){
        //а теперь тут мы уже можем перечислять экраны которые будут находиться во сложенном графе
        composable(Screen.NewsFeed.route) { newsFeedScreenContent() }
        composable(Screen.Comments.route) { commentsScreenContent() }

    }
}