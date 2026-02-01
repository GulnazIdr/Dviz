package com.example.dviz.domain

import com.example.dviz.domain.event.EventRepository
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

class EventRepoImpl @Inject constructor(
    localEventRepository: EventRepository,
    remoteEventRepository: EventRepository,
    supabaseClient: SupabaseClient
): EventRepository, BaseRepository<EventRepository>(
    localEventRepository,
    remoteEventRepository,
    supabaseClient
) {
    override suspend fun fetchEventList(
        eventPage: Int,
        placePage: Int,
        moviePage: Int
    ): FetchResult<List<Places>> {
        return getRepository().fetchEventList(eventPage,placePage,moviePage)
    }

    override suspend fun fetchCategoryList(): FetchResult<List<Category>> {
        return  getRepository().fetchCategoryList()
    }

    override suspend fun getPlaceById(id: Int): FetchResult<Place> {
        return getRepository().getPlaceById(id)
    }
}