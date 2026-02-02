package com.example.dviz.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ui_interface.buttons.CalendarMoveButton
import com.example.ui_interface.theme.LocalTypography
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(
    yearMonth: YearMonth,
    onPreviousMonthButtonClicked: (YearMonth) -> Unit,
    onNextMonthButtonClicked: (YearMonth) -> Unit,
) {
    Row {
        CalendarMoveButton(
            onArrow = {
                onPreviousMonthButtonClicked.invoke(yearMonth.minusMonths(1))
            }
        )
        Text(
            text = yearMonth.month.name,
            textAlign = TextAlign.Center,
            style = LocalTypography.current.titleBold.copy(
                fontSize = 11.sp
            ),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        CalendarMoveButton(
            isToRight = true,
            onArrow = {
                onNextMonthButtonClicked.invoke(yearMonth.plusMonths(1))
            }
        )
    }
}