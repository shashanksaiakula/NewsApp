package com.example.newsapp.navigation


sealed class AppNavigationSealed(val route: String) {
    object NewsScreen : AppNavigationSealed("home")
    object NewsDetailScreen : AppNavigationSealed("news_detail/{title}")
}