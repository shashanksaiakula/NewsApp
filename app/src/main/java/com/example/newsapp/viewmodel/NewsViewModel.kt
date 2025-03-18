package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _topHeadlines = MutableStateFlow<Result<HeadLineNews>>(Result.Loading)
    val topHeadlines: StateFlow<Result<HeadLineNews>> = _topHeadlines

    suspend fun getTopHeadlines(country: String, apiKey: String) {
        _topHeadlines.value = Result.Loading
        try {
            val response = newsRepository.getTopHeadlines(country, apiKey)
            if (response.isSuccessful) {
                _topHeadlines.value = Result.Success(response.body()!!)
            } else {
                _topHeadlines.value = Result.Error(Exception("API call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            _topHeadlines.value = Result.Error(e)
        }
    }
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}