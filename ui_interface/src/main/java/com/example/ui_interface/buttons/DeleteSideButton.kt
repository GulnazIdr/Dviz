package com.example.ui_interface.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.mainColor

@Composable
fun DeleteSideButton(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(end = 20.dp)
            .height(221.dp)
            .width(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(mainColor)
    ) {
        IconButton(
            onClick = {onDelete()},
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trash),
                contentDescription = "trash bin icon",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun DeleteSideButtonPreview() {
    DeleteSideButton(
        onDelete = {}
    )
}