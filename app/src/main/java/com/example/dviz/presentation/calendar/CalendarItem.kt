package com.example.dviz.presentation.calendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
    isChosen: Boolean,
    date: CalendarUi.Date,
    onClickListener: (CalendarUi.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    Log.d("is2", isFocused.toString())
    val calendarModifier =
        if(isFocused)
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
                if (isFocused) focusRequester.freeFocus()
                else focusRequester.requestFocus()
                onClickListener(date)
            }
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
    ){
        Text(
            text = date.dayOfMonth,
            style = LocalTypography.current.titleMedium4,
            modifier = Modifier.align(Alignment.Center),
            color =
                if (!date.isdayOfCurrentMonth) gray
                else if(isFocused) mainColor
                else if(date.isSelected) white
                else darkBlack
        )
    }
}

