package com.example.dviz.data.mappers

import com.example.dviz.data.api.events.EventDto
import com.example.dviz.data.api.events.EventsDto
import com.example.dviz.data.room.entities.CategoryEntity
import com.example.dviz.data.room.entities.EventEntity
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places

fun EventDto.toEvent(): Place{
    return Place(
        id = id,
        title = title,
        city = "",//location.slug,
        images = images.map { it.image },
        category = Category(categories[0]),
        price = ""
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

fun EventEntity.toPlace(): Place{
    return Place(
        id = id,
        title = title,
        city = cityId,
        images = images,
        category = Category(categoryId),
        address = address,
        bodyText = description,
        age_restriction = ageLimit.toString(),
        price = ""
    )
}

fun CategoryEntity.toCategory(): Category{
    return Category(name = category_name)
}
