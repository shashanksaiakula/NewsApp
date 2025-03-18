package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.screens.NewDetailScreen
import com.example.newsapp.presentation.screens.NewsScreen

@Composable
fun AppNaivagtion(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigationSealed.NewsScreen.route){
        composable(AppNavigationSealed.NewsScreen.route) {
            NewsScreen(navController = navController)
        }
        composable("news_detail/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            if (title != null) {
                NewDetailScreen(navController = navController,title= title)
            }
        }
    }
}