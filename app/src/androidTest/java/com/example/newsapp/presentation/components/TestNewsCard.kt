package com.example.newsapp.presentation.components

import android.media.Image
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.newsapp.data.model.Article
import org.junit.Rule
import org.junit.Test

class NewsCardTest() {
   val artical = Article(
       content = "content",
       description = "description",
       publishedAt = "publishedAt",
       source = null,
       title = "title",
       url = "url",
       urlToImage = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_120x44dp.png"
   )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNewsCard() {
        composeTestRule.setContent {
            NewsCard(
                artical,
            ){
            }
        }
        composeTestRule.onNodeWithText("title").assertExists()
        composeTestRule.onNodeWithTag("Bookmark").assertExists()
        composeTestRule.onNodeWithTag("NewsCard").assertExists()
    }

    @Test
    fun TestNewCardClick(){
        var clickCount = 0
        composeTestRule.setContent {
            NewsCard(
                artical,
            ){
                clickCount++
            }
        }
        composeTestRule.onNodeWithTag("NewsCard").performClick()

        assert(clickCount == 1)
    }

    @Test
    fun ImageInSideNewsCard() {
        composeTestRule.setContent {
            NewsCard(
                artical,
            ){

            }
        }
        val image = composeTestRule.onNodeWithTag("image", useUnmergedTree = true)
        image.assertExists()
        image.assertIsDisplayed()
        image.assertContentDescriptionContains("News Image")
    }
}