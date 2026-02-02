package com.example.dviz.data.api

import android.util.Log
import com.example.dviz.data.mappers.toCategory
import com.example.dviz.data.mappers.toPlace
import com.example.dviz.data.mappers.toPlaces
import com.example.dviz.data.api.places.CategoryDto
import com.example.dviz.data.mappers.toLocation
import com.example.dviz.data.room.LocalEventRepository
import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.event.EventRepository
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Location
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import javax.inject.Inject

class RemoteEventRepoImpl @Inject constructor(
    private val eventApi: EventApi,
    private val localEventRepository: LocalEventRepository
): EventRepository {

    override suspend fun fetchCategoryList(): FetchResult<List<Category>> {
        try {
            val fetched = mutableListOf(CategoryDto("Все"),
                CategoryDto("Фильмы"))
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
            val placeFetched = eventApi.fetchPlaceList(placePage).toPlaces()
            val eventFetched =  eventApi.fetchEventList(eventPage).toPlaces()
            val movieFetched = eventApi.fetchMovieList(moviePage).toPlaces()
            return FetchResult.Success(listOf(eventFetched, placeFetched, movieFetched))
        }catch (e: Exception){
            Log.e("fetching places error", "${e::class.simpleName} ${e::message}")
            return FetchResult.Error(e::message.name)
        }
    }

    override suspend fun getLocations(): FetchResult<List<Location>> {
        try {
            val fetched = eventApi.fetchCityList()
            return FetchResult.Success(fetched.map { it.toLocation() })
        }catch (e: Exception){
            Log.e("fetching cities error", "${e::class.simpleName} ${e::message}")
            return FetchResult.Error(e::message.name)
        }
    }
}