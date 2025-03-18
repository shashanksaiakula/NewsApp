package com.example.newsapp.data.db


import androidx.room.TypeConverter
import com.example.newsapp.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return sourceString?.let {
            val type = object : TypeToken<Source>() {}.type
            Gson().fromJson(it, type)
        }
    }
}