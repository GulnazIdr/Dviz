package com.example.dviz.data.api.movie

import com.example.dviz.data.api.ImageDto

data class MovieDto(
    val id: Int,
    val title: String,
    val budget: Int,
    val images: List<ImageDto>,
    val age_restriction: String
)
