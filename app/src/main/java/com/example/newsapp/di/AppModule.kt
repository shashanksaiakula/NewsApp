package com.example.newsapp.di

import com.example.newsapp.data.network.NewsUsApi
import com.example.newsapp.data.network.RetrofitClient
import com.example.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.retrofit
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api : NewsUsApi): NewsRepository {
        return NewsRepository(api)
    }


    @Provides
    @Singleton
    fun provideNewsUsApi(retrofit: Retrofit): NewsUsApi {
        return retrofit.create(NewsUsApi::class.java)
    }
}