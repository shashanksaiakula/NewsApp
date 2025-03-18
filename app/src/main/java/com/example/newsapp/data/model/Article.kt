package com.example.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
)