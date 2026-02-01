package com.example.dviz.data.api.places

import com.example.dviz.data.api.ImageDto

data class PlaceDto(
    val id: Int,
    val title: String,
    val images: List<ImageDto>,
    val categories: List<String>,
    val location: String,
)

