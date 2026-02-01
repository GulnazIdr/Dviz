package com.example.dviz.data.api.movie

import java.time.Year

data class MovieDetailsDto(
    val id: Int,
    val body_text: String,
    val country: String,
    val url: String,
    val running_time: Int,
    val year: Year,
    val age_restriction: String
)
