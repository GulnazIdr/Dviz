package com.example.dviz.data.api.places

data class PlaceDetailsDto(
    val id: Int,
    val address: String,
    val bodyText: String,
    val site_url: String,
    val age_restriction: String
)
