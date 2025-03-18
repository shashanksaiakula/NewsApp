package com.example.newsapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.presentation.components.NewsCard
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.Result
import kotlinx.coroutines.coroutineScope

@Composable
fun NewsScreen(modifier: Modifier = Modifier, viewModel: NewsViewModel = viewModel()) {

    val articales = viewModel.topHeadlines.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getTopHeadlines("us", BuildConfig.API_KEY)
    }

    when(articales){
        is Result.Loading ->{

        }
        is Result.Success ->{
            val articlesData = (articales as Result.Success<HeadLineNews>).data.articles
            LazyColumn {
                items(articlesData){
                    if(it.description != null)
                   NewsCard(it)
                }
            }
        }
        is Result.Error -> {
            val errorMessage = (articales as Result.Error).exception.message ?: "Unknown error"
            Text(text = "Error: $errorMessage", modifier = Modifier.padding(16.dp))
        }
    }
}