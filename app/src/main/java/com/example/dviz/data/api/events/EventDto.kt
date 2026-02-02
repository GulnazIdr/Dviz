package com.example.dviz.data.api.events

import com.example.dviz.data.api.ImageDto
import com.example.dviz.data.api.places.CategoryDto
import java.math.BigInteger

data class EventDto(
    val id: Int,
    val title: String,
    val body_text: String,
    val age_restriction: String,
    val images: List<ImageDto>,
    val categories: List<String>,
    val price: String
)

data class DateDto(
    val start: BigInteger,
    val end: BigInteger
)

data class LocationDto(
    val slug: String
)
