package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.victoryvalery.jetpackvk.navigation.AppNavGraph
import com.victoryvalery.jetpackvk.navigation.Screen
import com.victoryvalery.jetpackvk.ui.NavigationItem.Favourite
import com.victoryvalery.jetpackvk.ui.NavigationItem.Home
import com.victoryvalery.jetpackvk.ui.NavigationItem.Profile

@Composable
fun VkNewsMainScreen(viewModel: MainViewModel) {

    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(72.dp)
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(Home, Favourite, Profile)
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.titleResId)) },
                        label = { Text(stringResource(item.titleResId)) },
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navHostController.navigate(item.screen.route) {
                                launchSingleTop = true //только один фрагмент сверху
                                restoreState = true //восстановление сохраненного стейта
                                popUpTo(Screen.NewsFeed.route) {//возврат к основному экрану
                                    saveState = true
                                } //сохранение стейта при удалении экрана из бэкстека
                            }
                        },
                    )
                }
            }
        }
    )
    {
        AppNavGraph(
            navController = navHostController,
            homeScreenContent = { HomeScreen(viewModel = viewModel, paddingValues = it) },
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