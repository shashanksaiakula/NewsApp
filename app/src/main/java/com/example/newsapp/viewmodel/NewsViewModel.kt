package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.db.NewsDao
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.HeadLineNews
import com.example.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val newsDao: NewsDao
) : ViewModel() {

    private val _topHeadlines = MutableStateFlow<Result<HeadLineNews>>(Result.Loading)
    val topHeadlines: StateFlow<Result<HeadLineNews>> = _topHeadlines

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    fun getTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            _topHeadlines.value = Result.Loading
            try {
                val response = newsRepository.getTopHeadlines(country, apiKey)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _topHeadlines.value = Result.Success(it)
                        newsDao.insertArticles(it.articles)
                    } ?: run {
                        _topHeadlines.value = Result.Error(Exception("Empty response body"))
                    }
                } else {
                    _topHeadlines.value = Result.Error(Exception("API call failed with code: ${response.code()}"))
                }
            } catch (e: Exception) {
                _topHeadlines.value = Result.Error(e)
            }
        }
    }

    fun getNewDetails(title : String){
        viewModelScope.launch {
            _article.value = newsDao.getDataById(title)
        }
    }
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}