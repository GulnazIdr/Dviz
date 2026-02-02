package com.example.dviz.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.events.EventDateTime
import com.example.ui_interface.models.CalendarUi
import com.example.ui_interface.theme.white
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    yearMonth: YearMonth,
    onPreviousMonthButtonClicked: (YearMonth) -> Unit,
    onNextMonthButtonClicked: (YearMonth) -> Unit,
    dates: List<CalendarUi.Date>,
    onDateClickListener: (EventDateTime) -> Unit,
    modifier: Modifier = Modifier
) {
    var isChosen by remember { mutableStateOf(false) }
    Box(
        modifier
            .clip(RoundedCornerShape(4.5.dp))
            .background(white)
            .padding(13.dp)
    ) {
        Column {
            CalendarHeader(
                yearMonth = yearMonth,
                onPreviousMonthButtonClicked = onPreviousMonthButtonClicked,
                onNextMonthButtonClicked = onNextMonthButtonClicked
            )

            Spacer(modifier = Modifier.height(11.dp))

            CalendarDays(
                dates = dates,
                onDateClickListener = {
                    isChosen = !isChosen
                    onDateClickListener(EventDateTime(yearMonth, it.dayOfMonth.toInt()))
                },
                isChosen = isChosen
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun CalendarPrev() {
    Calendar(
        YearMonth.now(),
        onPreviousMonthButtonClicked = {},
        onNextMonthButtonClicked = {},
        dates = listOf(),
        onDateClickListener = {}
    )
}