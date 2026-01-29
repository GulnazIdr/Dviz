package com.example.ui_interface.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.grayLight

@Composable
fun CalendarMoveButton(
    isToRight: Boolean = false,
    onArrow: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(17.3.dp)
            .border(1.5.dp, grayLight, RoundedCornerShape(4.dp))
            .clickable(onClick = {onArrow()})

    ){
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "icon",
            modifier = Modifier
                .rotate(
                    if (isToRight) 180f
                    else 0f
                ).align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun CalendarMoveButtonPrev() {
    CalendarMoveButton(
        onArrow = {},
        isToRight = true
    )
}