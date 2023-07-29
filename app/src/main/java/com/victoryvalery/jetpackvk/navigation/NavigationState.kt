package com.victoryvalery.jetpackvk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            //Если стартовый экран просто экран, то можно исп-ть navHostController.graph.startDestinationId для получения id начального экрана
            //Если же стартовый экран сам граф, то ...findStartDestination().id
            popUpTo(navHostController.graph.findStartDestination().id) {//возврат к основному экрану
                saveState = true
            } //сохранение стейта при удалении экрана из бэкстека
            launchSingleTop = true //только один фрагмент сверху
            restoreState = true //восстановление сохраненного стейта
        }
    }

    fun navigateToComments() {
        navHostController.navigate(Screen.Comments.route)
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}