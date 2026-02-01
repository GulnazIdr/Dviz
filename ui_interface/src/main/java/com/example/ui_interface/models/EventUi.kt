package com.example.ui_interface.models

import java.time.YearMonth

data class EventUi(
    val id: Int,
    val title: String,
    val price: Double,
    val images: List<String> = emptyList(),
    val city: CityUi,
    var dateTime: CalendarUi = CalendarUi(YearMonth.now(), dates = listOf()),
    val categoryUi: List<CategoryUi>,
    val description: String = "",
    val ageLimit: Int = 0,
    val address: String = "",
    val cartAmount: Int = 0
)
