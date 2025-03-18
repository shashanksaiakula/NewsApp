package com.example.newsapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class LoderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoder() {
        composeTestRule.setContent {
            Loader()
        }
        composeTestRule.onNodeWithTag("loder").assertExists()
    }
}