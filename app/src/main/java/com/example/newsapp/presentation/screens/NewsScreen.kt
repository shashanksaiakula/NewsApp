package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.BuildConfig
import com.example.newsapp.presentation.components.Loader
import com.example.newsapp.presentation.components.NewsCard
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.Result

@Composable
fun NewsScreen(modifier: Modifier = Modifier,
               viewModel: NewsViewModel = hiltViewModel(),
               navController: NavController) {

    LaunchedEffect(Unit) {
        viewModel.getTopHeadlines("us", BuildConfig.API_KEY)
    }
    val articales = viewModel.topHeadlines.collectAsState().value

    when(articales){
        is Result.Loading -> {
            Loader()
        }
        is Result.Success ->{
            val articlesData = articales.data.articles
            LazyColumn {
                items(articlesData){
                    if(it.description != null)
                   NewsCard(it){
                       navController.navigate("news_detail/${it.title}")
                   }
                }
            }
        }
        is Result.Error -> {
            val errorMessage = articales.exception.message ?: "Unknown error"
            Text(text = "Error: $errorMessage", modifier = Modifier.padding(16.dp))
        }
    }
}