package com.example.dviz.data.api.movie

import com.example.dviz.data.api.ImageDto

//https://kudago.com/public-api/v1.2/movies/?fields=id,title,poster,url,description,country,year,running_time,age_restriction,images,budget
data class MovieDto(
    val id: Int,
    val title: String,
    val budget: Int,
    val images: List<ImageDto>,
    val age_restriction: String
)
