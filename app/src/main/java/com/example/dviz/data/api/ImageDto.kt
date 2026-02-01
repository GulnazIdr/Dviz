package com.example.dviz.data.api

data class ImageDto(
    val image: String,
    val source: ImageSourceDto
)

data class ImageSourceDto(
    val name: String,
    val link: String
)