package com.example.newsapp.presentation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.data.model.Source
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.Result
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NewsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    private val mockViewModel = mockk<NewsViewModel>(relaxed = true)

    @Before
    fun init() {
        hiltRule.inject()

        every { mockViewModel.topHeadlines } answers {
            MutableStateFlow(
                Result.Success(
                    HeadLineNews(
                        status = "ok",
                        totalResults = 1,
                        articles = listOf(
                            Article(
                                id = 1,
                                title = "Sample News",
                                description = "Description",
                                url = "https://example.com",
                                urlToImage = "https://example.com/image.jpg",
                                content = "News content",
                                publishedAt = "2025-03-17T11:47:23Z",
                                source = Source("ABC")
                            )
                        )
                    )
                )
            )
        }
    }

    @Test
    fun testNewsScreen() {
        composeRule.setContent {
            val navController = rememberNavController()
            NewsScreen(navController = navController, viewModel = mockViewModel)
        }

        composeRule.onNodeWithText("Sample News").assertExists()
    }

    @Test
    fun testErrorState() {
        every { mockViewModel.topHeadlines } answers {
            MutableStateFlow(
                Result.Error(
                    Throwable("Network error")
                )
            )
        }
        composeRule.setContent {
            val navController = rememberNavController()
            NewsScreen(navController = navController, viewModel = mockViewModel)
        }
        composeRule.onNodeWithText("Error: Network error", useUnmergedTree = true).assertExists()
    }

    @Test
    fun testLoadingState() {
        every { mockViewModel.topHeadlines } answers {
            MutableStateFlow(
                Result.Loading
            )
        }
        composeRule.setContent {
            val navController = rememberNavController()
            NewsScreen(navController = navController, viewModel = mockViewModel)
        }
        composeRule.onNodeWithTag("loader").assertExists()

    }
}
