package com.example.dviz.presentation.mappers

import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Location
import com.example.dviz.domain.models.Movie
import com.example.dviz.domain.models.Place
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.CityUi
import com.example.dviz.presentation.events.EventUi
import com.example.dviz.presentation.events.LocationUi
import com.example.ui_interface.models.MovieUi

fun Place.toEventUi(): EventUi{
    return EventUi(
        id = id,
        title = title,
        city = CityUi(slug = city, name = city),
        images = images.ifEmpty { emptyList() },
        categoryUi = category.toCategoryUi(),
        price = price,
    )
}

fun FetchResult<Place>.toFetchedResultEvUi(): FetchResult<EventUi>{
    val eventUi: EventUi? = this.data?.toEventUi()
    return when{
        eventUi != null -> FetchResult.Success(eventUi)
        message != null -> FetchResult.Error(message)
        else -> FetchResult.Initial()
    }
}
fun Category.toCategoryUi(): CategoryUi{
    return CategoryUi(
        name = name
    )
}

fun Location.toLocationUi() = LocationUi(id,name)