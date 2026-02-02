package com.example.dviz.data.api

data class LocationsDto(
    val locationList: List<LocationDto>
)
data class LocationDto (
    val slug: String,
    val name: String
)