package com.example.dviz.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_interface.models.CalendarUi
import com.example.ui_interface.theme.LocalTypography

@Composable
fun CalendarDays(
    isChosen: Boolean,
    dates: List<CalendarUi.Date>,
    onDateClickListener: (CalendarUi.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    val weekList = listOf("пн","вт","ср","чт","пт","сб","вс")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(7) {
                Text(
                    text = weekList[it],
                    style = LocalTypography.current.titleMedium3.copy(
                        fontSize = 10.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(11.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var index = 0
            repeat(5) { weekIndex ->
                if (index >= dates.size) return@repeat
                Row {
                    repeat(7) { dayIndex ->
                        val item = dates[index]
                        CalendarItem(
                            date = item,
                            onClickListener = { onDateClickListener(it) },
                            modifier = Modifier.weight(1f),
                            isChosen = isChosen
                        )
                        if (dayIndex != 6)
                            Spacer(modifier = Modifier.width(5.4.dp))

                        index ++
                    }
                }

                if (index != 4)
                    Spacer(modifier = Modifier.height(5.4.dp))

            }
        }
    }
}
