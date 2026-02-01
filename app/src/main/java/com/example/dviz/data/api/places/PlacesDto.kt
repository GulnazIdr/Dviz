package com.example.dviz.data.api.places

data class PlacesDto(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PlaceDto>
)