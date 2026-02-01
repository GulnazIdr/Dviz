package com.example.dviz.presentation.mappers

import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Movie
import com.example.dviz.domain.models.Place
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.CityUi
import com.example.ui_interface.models.EventUi
import com.example.ui_interface.models.MovieUi
import com.example.ui_interface.models.PlaceUi
import java.time.LocalDate
import java.time.LocalDateTime

fun Place.toEventUi(): EventUi{
    return EventUi(
        id = id,
        title = title,
        city = CityUi(slug = city, name = city),
        images = images.ifEmpty { emptyList() },
        categoryUi = categories.map { it.toCategoryUi() },
        price = 1.0,
    )
}

fun Movie.toMovieUi(): MovieUi{
    return MovieUi(
        id = id,
        title = title,
        price = budget,
        images = images,
        description = body_text,
        country = country,
        movie_source_url = url,
        running_time = running_time,
        year = year,
        age_restriction = age_restriction.takeWhile { it.isDigit() }.toInt(),
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
        id = id,
        name = name
    )
}