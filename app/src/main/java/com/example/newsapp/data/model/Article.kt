package com.example.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newsapp.data.db.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
@TypeConverters(Converters::class)
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val content : String?,
    val publishedAt : String?,
    @SerializedName("source")
val source: Source?
)

data class Source(
    @SerializedName("name")
    val name: String?
)