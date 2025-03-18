package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.db.NewsDao
import com.example.newsapp.data.db.NewsDatabase
import com.example.newsapp.data.network.NewsUsApi
import com.example.newsapp.data.network.RetrofitClient
import com.example.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }
}