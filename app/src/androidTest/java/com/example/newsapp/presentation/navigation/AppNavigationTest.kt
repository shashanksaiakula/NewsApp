package com.example.newsapp.presentation.navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.MainActivity
import com.example.newsapp.navigation.AppNavigationSealed
import com.example.newsapp.presentation.screens.NewDetailScreen
import com.example.newsapp.presentation.screens.NewsScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationToNewsScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NewsScreen(navController = navController)
        }

        composeTestRule.onNodeWithText(AppNavigationSealed.NewsScreen.route).assertExists()
    }

    @Test
    fun testNavigationToNewsDetailScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NewDetailScreen(navController = navController, title = "Sample News Title")
        }


        composeTestRule.onNodeWithText(AppNavigationSealed.NewsDetailScreen.route).assertExists()
    }
}
