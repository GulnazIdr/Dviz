package com.example.dviz.data.mappers

import com.example.dviz.data.api.movie.MovieDto
import com.example.dviz.data.api.places.MoviesDto
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places

fun MovieDto.toPlace(): Place{
    return Place(
        id = id,
        title = title,
        city = "unknown",
        images = images.map { it.image },
        category = Category("Фильмы"),
        price = ""
    )
}

fun MoviesDto.toPlaces(): Places{
    return Places(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toPlace() }
    )
}
