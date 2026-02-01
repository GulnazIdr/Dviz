package com.example.ui_interface.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ui_interface.models.CalendarUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.darkBlack
import com.example.ui_interface.theme.darkPurple
import com.example.ui_interface.theme.gray
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun CalendarItem(
    date: CalendarUi.Date,
    onClickListener: (CalendarUi.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    var isChosen by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isChosen = false
    }

    val calendarModifier =
        if(isChosen)
            modifier
                .border(1.dp, mainColor, RoundedCornerShape(4.dp))
        else if(date.isSelected)
            modifier
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, darkPurple)
                .background(mainColor)
        else modifier
    Box(
        modifier = calendarModifier
            .height(17.3.dp)
            .clickable {
                onClickListener(date)
                isChosen = true
            }
    ){
        Text(
            text = date.dayOfMonth,
            style = LocalTypography.current.titleMedium4,
            modifier = Modifier.align(Alignment.Center),
            color =
                if (!date.isdayOfCurrentMonth) gray
                else if(isChosen) mainColor
                else if(date.isSelected) white
                else darkBlack
        )
    }
}

