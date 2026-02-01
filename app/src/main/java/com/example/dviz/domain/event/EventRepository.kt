package com.example.dviz.domain.event

import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Movies
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places

interface EventRepository {
    suspend fun fetchEventList(
        eventPage: Int, placePage: Int, moviePage: Int
    ): FetchResult<List<Places>>
    suspend fun fetchCategoryList(): FetchResult<List<Category>>
    suspend fun getPlaceById(id: Int): FetchResult<Place>
}