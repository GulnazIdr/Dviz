package com.example.dviz.data.api.events

data class EventsDto(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<EventDto>
)
