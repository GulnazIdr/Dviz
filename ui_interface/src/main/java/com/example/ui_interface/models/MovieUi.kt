package com.example.ui_interface.models

import java.time.LocalDateTime
import java.time.Year
import java.time.YearMonth

data class MovieUi(
    val id: Int,
    val dates: CalendarUi = CalendarUi(YearMonth.now(), dates = listOf()),
    val title: String,
    val price: Int,
    val images: List<String>,
    val description: String = "",
    val country: String = "",
    val movie_source_url: String = "",
    val running_time: Int = 0,
    val year: Year = Year.now(),
    val age_restriction: Int = 0
)
