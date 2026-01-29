package com.example.ui_interface.calendar

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
                    dayOfMonth = if (date.monthValue == yearMonth.monthValue) {
                        "${date.dayOfMonth}"
                    } else {
                        ""
                    },
                    isSelected =
                        date.isEqual(LocalDate.now()) && date.monthValue == yearMonth.monthValue
                )
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun YearMonth.getDayOfMonthStartingFromMonday(): List<LocalDate> {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        val firstMondayOfMonth = firstDayOfMonth.with(DayOfWeek.MONDAY)
        val firstDayOfNextMonth = firstDayOfMonth.plusMonths(1)

        return generateSequence(firstMondayOfMonth) { it.plusDays(1) }
            .takeWhile { it.isBefore(firstDayOfNextMonth) }
            .toList()
    }
}
