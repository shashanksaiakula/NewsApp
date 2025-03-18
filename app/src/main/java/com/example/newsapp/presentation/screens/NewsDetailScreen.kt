package com.example.newsapp.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.presentation.components.Loader
import com.example.newsapp.utils.AppContect
import com.example.newsapp.viewmodel.NewsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    viewModel: NewsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getNewDetails(title)
    }
    val article = viewModel.article.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (article != null) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.source?.name ?: "Unknown",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = if(article.publishedAt != null) AppContect.timeConversion(article.publishedAt)
                    else "Not Available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = article.description ?: "No description available",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.content ?: "No content available",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            Loader()
        }
    }
}

