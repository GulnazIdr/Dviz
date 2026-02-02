package com.example.dviz.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ui_interface.models.CalendarUi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

class CalendarDataSource @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDates(yearMonth: YearMonth): List<CalendarUi.Date> {
        return yearMonth.getDayOfMonthStartingFromMonday()
            .map { date ->
                CalendarUi.Date(
                    dayOfMonth = "${date.dayOfMonth}",
                    isSelected = date.isEqual(LocalDate.now()) &&
                            date.monthValue == yearMonth.monthValue,
                    isdayOfCurrentMonth = date.monthValue == yearMonth.monthValue
                )
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun YearMonth.getDayOfMonthStartingFromMonday(): List<LocalDate> {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        val firstMondayOfMonth = firstDayOfMonth.with(DayOfWeek.MONDAY)
        val lastDayOfCalendar = firstDayOfMonth.plusWeeks(4)
            .with(DayOfWeek.SUNDAY).plusDays(1)

        return generateSequence(firstMondayOfMonth) { it.plusDays(1) }
            .takeWhile { it.isBefore(lastDayOfCalendar) }
            .toList()
    }
}
