package com.example.newsapp.presentation.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source
import com.example.newsapp.utils.AppContect
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NewDetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    private lateinit var viewModel: NewsViewModel
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()

        viewModel = mockk(relaxed = true)
        navController = mockk(relaxed = true)

        every { viewModel.article } returns MutableStateFlow(
            Article(
                title = "Test News",
                description = "This is a test description",
                content = "This is the content of the news article",
                urlToImage = "https://example.com/news.jpg",
                publishedAt = "2025-03-18T12:00:00Z",
                source = Source(name = "Test Source"),
                id = 1,
                url = "https://example.com/news"
            )
        )
    }

    @Test
    fun testNewsDetailsDisplayedCorrectly() {
        composeRule.setContent {
            NewDetailScreen(
                navController = navController,
                title = "Test News",
                viewModel = viewModel
            )
        }

        composeRule.onNodeWithContentDescription("News Image").assertExists()

        composeRule.onNodeWithText("Test Source").assertExists()

        composeRule.onNodeWithText(AppContect.timeConversion("2025-03-18T12:00:00Z")).assertExists()

        composeRule.onNodeWithText("Test News").assertExists()

        composeRule.onNodeWithText("This is a test description").assertExists()

        composeRule.onNodeWithText("This is the content of the news article").assertExists()
    }

    @Test
    fun testLoaderIsDisplayedWhenArticleIsNull() {
        every { viewModel.article } returns MutableStateFlow(null)  // Set article as null

        composeRule.setContent {
            NewDetailScreen(
                navController = navController,
                title = "Test News",
                viewModel = viewModel
            )
        }

        composeRule.onNodeWithTag("loader", useUnmergedTree = true).assertExists()

    }
}
