package com.example.dviz.presentation.events

import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.CityUi
import java.time.YearMonth

data class EventUi(
    val id: Int,
    val title: String,
    var price: String,
    val images: List<String> = emptyList(),
    val city: CityUi,
    var dateTime: EventDateTime = EventDateTime(YearMonth.now(), 0),
    val categoryUi: CategoryUi,
    val description: String = "",
    val ageLimit: Int = 0,
    val address: String = "",
    val cartAmount: Int = 0
)