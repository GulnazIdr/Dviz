package com.example.dviz.data.api.mappers

import com.example.dviz.data.api.events.EventDto
import com.example.dviz.data.api.events.EventsDto
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import java.time.LocalDate

fun EventDto.toEvent(): Place{
    return Place(
        id = id,
        title = title,
        city = "",//location.slug,
        images = images.map { it.image },
        categories = categories.map { Category("", it) }
    )
}

fun EventsDto.toPlaces(): Places{
    return Places(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toEvent() }
    )
}
