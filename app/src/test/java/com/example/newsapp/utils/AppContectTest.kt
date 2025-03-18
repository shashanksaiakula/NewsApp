package com.example.newsapp.utils

import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class AppContectTest {

    @Test
    fun `timeConversion returns correct local time`() {
        val isoTime = "2024-03-18T14:30:00Z"

        val expectedTime = Instant.parse(isoTime)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("hh:mm:ss a"))

        val result = AppContect.timeConversion(isoTime)

        assertEquals(expectedTime, result)
    }
}
