package com.victoryvalery.jetpackvk.navigation

sealed class Screen(
    val route: String
) {
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favourite : Screen(ROUTE_FAVOURITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS)

    companion object {
        private const val ROUTE_HOME = "home"
        private const val ROUTE_COMMENTS = "comments"
        private const val ROUTE_NEWS_FEED = "news feed"
        private const val ROUTE_FAVOURITE = "favourite"
        private const val ROUTE_PROFILE = "profile"
    }
}
