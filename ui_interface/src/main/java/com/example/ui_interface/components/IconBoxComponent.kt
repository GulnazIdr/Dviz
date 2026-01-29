package com.example.ui_interface.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_interface.theme.lightgray

@Composable
fun IconBoxComponent(
    id: Int?,
    backgroundColor: Color,
    size: Dp,
    iconSize: Dp = Dp.Unspecified,
    shape: Shape,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(color = backgroundColor)
    ) {
        if (id != null)
        Icon(
            painter = painterResource(id),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(iconSize),
            tint = tint
        )
    }
}
