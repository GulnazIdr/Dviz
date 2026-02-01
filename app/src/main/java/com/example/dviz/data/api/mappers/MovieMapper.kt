package com.example.dviz.data.api.mappers

import com.example.dviz.data.api.movie.MovieDto
import com.example.dviz.data.api.places.MoviesDto
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import java.time.LocalDate

fun MovieDto.toPlace(): Place{
    return Place(
        id = id,
        title = title,
        city = "unknown",
        images = images.map { it.image },
        categories = listOf(Category("movie", "Фильмы"))
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
