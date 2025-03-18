package com.example.newsapp.data.model

data class HeadLineNews(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
