package com.example.dviz.data.api.mappers

import com.example.dviz.data.api.places.CategoryDto
import com.example.dviz.data.api.places.PlaceDetailsDto
import com.example.dviz.data.api.places.PlaceDto
import com.example.dviz.data.api.places.PlacesDto
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places

fun PlaceDto.toPlace(): Place{
    return Place(
        id = id,
        title = title,
        city = location,
        images = images.map { it.image },
        categories = categories.map { Category(id = "slug", name = it)}
    )
}

fun PlaceDetailsDto.toPlace(): Place{
    return Place(
        id = id,
        title = "",
        city ="",
        images = listOf(),
        categories = listOf()
    )
}

fun PlacesDto.toPlaces(): Places{
    return Places(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toPlace() }
    )
}

fun CategoryDto.toCategory(): Category{
    return Category(
        id = slug,
        name = name
    )
}