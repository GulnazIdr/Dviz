package com.example.dviz.data.api

import com.example.dviz.data.api.events.EventsDto
import com.example.dviz.data.api.places.CategoryDto
import com.example.dviz.data.api.places.MoviesDto
import com.example.dviz.data.api.places.PlaceDetailsDto
import com.example.dviz.data.api.places.PlacesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {
    @GET("events")
    suspend fun fetchEventList(
        @Query("page")page: Int,
        @Query("fields")fields: String = "id,title,body_text,categories,age_restriction,images,price"
    ): EventsDto

    @GET("places")
    suspend fun fetchPlaceList(
        @Query("page")page: Int,
        @Query("fields")fields: String = "id,title,categories,location,images"
    ): PlacesDto

    @GET("locations")
    suspend fun fetchCityList(
        @Query("lang")fields: String = "rus"
    ): List<LocationDto>

    @GET("places/{id}")
    suspend fun getPlaceById(
        @Query("id")id: Int,
        @Query("fields")fields: String = "id,address,body_text,site_url,age_restriction"
    ): PlaceDetailsDto

    @GET("movies")
    suspend fun fetchMovieList(
        @Query("page")page: Int,
        @Query("fields")fields: String = "id,title,budget,age_restriction,images"
    ): MoviesDto

    @GET("place-categories")
    suspend fun fetchCategories(): List<CategoryDto>
}