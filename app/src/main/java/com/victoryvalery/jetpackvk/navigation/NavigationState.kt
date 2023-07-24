package com.victoryvalery.jetpackvk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true //только один фрагмент сверху
            restoreState = true //восстановление сохраненного стейта
            popUpTo(navHostController.graph.startDestinationId) {//возврат к основному экрану
                saveState = true
            } //сохранение стейта при удалении экрана из бэкстека
        }
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