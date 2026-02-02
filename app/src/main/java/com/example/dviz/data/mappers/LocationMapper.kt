package com.example.dviz.data.mappers

import com.example.dviz.data.api.LocationDto
import com.example.dviz.domain.models.Location

fun LocationDto.toLocation() =
    Location(
        id = slug,
        name = name
    )