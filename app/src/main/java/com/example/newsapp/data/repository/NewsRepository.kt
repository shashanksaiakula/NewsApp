package com.example.newsapp.data.repository

import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.data.network.NewsUsApi
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api : NewsUsApi) {
    suspend fun getTopHeadlines(country : String, apiKey : String) : Response<HeadLineNews> =
        api.getTopHeadlines(country, apiKey)
}
