package com.example.dviz.data.api

import android.util.Log
import com.example.dviz.data.api.mappers.toCategory
import com.example.dviz.data.api.mappers.toPlace
import com.example.dviz.data.api.mappers.toPlaces
import com.example.dviz.data.api.places.CategoryDto
import com.example.dviz.data.room.LocalEventRepository
import com.example.dviz.data.room.dao.EventDao
import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.event.EventRepository
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import javax.inject.Inject

class RemoteEventRepoImpl @Inject constructor(
    private val eventApi: EventApi,
    private val localEventRepository: LocalEventRepository
): EventRepository {

    override suspend fun fetchCategoryList(): FetchResult<List<Category>> {
        try {
            val fetched = mutableListOf(CategoryDto("common","Общее"),
                CategoryDto("movie","Фильмы"))
            fetched += eventApi.fetchCategories()
            return FetchResult.Success(fetched.map { it.toCategory() })
        }catch (e: Exception){
            Log.e("fetching categories error", "${e::class.simpleName} ${e::message}")
            return FetchResult.Error(e::message.name)
        }
    }

    override suspend fun getPlaceById(id: Int): FetchResult<Place> {
        try {
            val fetched = eventApi.getPlaceById(id)
            Log.d("check", fetched.toString())
            return FetchResult.Success(fetched.toPlace())
        }catch (e: Exception){
            Log.e("fetching place error", "${e::class.simpleName} ${e::message}")
            return FetchResult.Error(e::message.name)
        }
    }

    override suspend fun fetchEventList(eventPage: Int, placePage: Int, moviePage: Int)
    : FetchResult<List<Places>> {
        try {
            val movieFetched = eventApi.fetchMovieList(moviePage)
              .toPlaces()
            Log.d("something1", "")
            val placeFetched = eventApi.fetchPlaceList(placePage)
                .toPlaces()
            Log.d("something2", "")
            val eventFetched =  eventApi.fetchEventList(eventPage)
                .toPlaces()
            Log.d("something3", "")

            return FetchResult.Success(listOf(eventFetched, placeFetched, movieFetched))
        }catch (e: Exception){
            Log.e("fetching places error", "${e::class.simpleName} ${e::message}")
            return FetchResult.Error(e::message.name)
        }
    }
}