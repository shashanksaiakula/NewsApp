package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
            .testTag("loader"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}