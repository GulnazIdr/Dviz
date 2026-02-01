package com.example.dviz.domain.models

import java.time.Year

data class Movie(
    val id: Int,
    val title: String,
    val budget: Int,
    val images: List<String>,
    val body_text: String = "",
    val country: String = "",
    val url: String = "",
    val running_time: Int = 0,
    val year: Year = Year.now(),
    val age_restriction: String = ""
)

