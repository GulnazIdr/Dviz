package com.example.ui_interface.models

import java.time.YearMonth

data class CalendarUi(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    )
}