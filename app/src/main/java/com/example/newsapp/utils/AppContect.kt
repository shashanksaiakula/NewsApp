package com.example.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AppContect {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun timeConversion(time: String): String {
            val instant = Instant.parse(time) // covert into string
            val zoneId = ZoneId.systemDefault() // get the time zone
            val zdt = instant.atZone(zoneId) // covert to local time zone
            val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a") // tome formate
            return zdt.format(formatter)
        }
    }
}