package com.example.ui_interface.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.theme.mainColor

@Composable
fun SideButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .height(221.dp)
            .width(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(mainColor)
    ) {
        Box(
            modifier = Modifier.align(Alignment.Center)
        ){
            content()
        }
    }
}

@Preview
@Composable
private fun SideButtonPrev() {
    SideButton(
        content = {}
    )
}