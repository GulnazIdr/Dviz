package com.example.dviz.data.api.places

import com.example.dviz.data.api.movie.MovieDto

data class MoviesDto(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<MovieDto>
)