package com.example.newsapp.data.network

import com.example.newsapp.data.model.HeadLineNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsUsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ) : Response<HeadLineNews>
}