package com.example.newsapp.viewmodel

import com.example.newsapp.data.db.NewsDao
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.repository.NewsRepository
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsRepository: NewsRepository
    private lateinit var newsDao: NewsDao

    private val mockHeadlines = HeadLineNews(
        articles = listOf(
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
        ),
        status = "ok",
        totalResults = 1
    )

    @Before
    fun setUp() {
        newsRepository = mockk(relaxed = true)
        newsDao = mockk(relaxed = true)
        viewModel = NewsViewModel(newsRepository, newsDao)
    }

    @Test
    fun `getTopHeadlines() - success response`() = runTest {

        coEvery { newsRepository.getTopHeadlines("us", "api_key") } returns
                Response.success(
            mockHeadlines
        )

        viewModel.getTopHeadlines("us", "api_key")
        advanceUntilIdle()
        val result = viewModel.topHeadlines.first()

        assertTrue(result is Result.Success)
        assertEquals("Test News", (result as Result.Success).data.articles[0].title)

        coVerify { newsDao.insertArticles(any()) }
    }

    @Test
    fun `getTopHeadlines() - failure response`() = runTest {

        coEvery { newsRepository.getTopHeadlines("us", "api_key") } returns Response.error(
            500,
            mockk(relaxed = true)
        )

        viewModel.getTopHeadlines("us", "api_key")
        advanceUntilIdle()
        val result = viewModel.topHeadlines.first()

        assertTrue(result is Result.Error)
        assertEquals("API call failed with code: 500", (result as Result.Error).exception.message)
    }

    @Test
    fun `getNewDetails() - fetches correct article`() = runTest {
        coEvery { newsDao.getDataById("Test News") } returns mockHeadlines.articles[0]

        viewModel.getNewDetails("Test News")
        advanceUntilIdle()
        val article = viewModel.article.first()

        assertNotNull(article)
        assertEquals("Test News", article?.title)
        assertEquals("This is the content of the news article", article?.content)
    }
}


@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher() {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}
