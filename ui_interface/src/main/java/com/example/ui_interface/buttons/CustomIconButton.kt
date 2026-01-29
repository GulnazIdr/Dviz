package com.example.ui_interface.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.white

@Composable
fun CustomIconButton(
    iconId: Int,
    innerPadding: PaddingValues = PaddingValues(10.dp),
    backgroundColor: Color = white,
    size: Dp = Dp.Unspecified,
    iconSize: Dp = Dp.Unspecified,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(innerPadding)
    ){
        Icon(
            painter = painterResource(iconId),
            contentDescription = "icon",
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview
@Composable
private fun CustomIconButtonPrev() {
    CustomIconButton(
        iconId = R.drawable.arrow,
        innerPadding = PaddingValues(10.dp)
    )
}