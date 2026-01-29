package com.example.ui_interface.places

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun Scene(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(85.dp)
            .height(38.dp)
            .clip(RoundedCornerShape(10.dp, 10.dp, 20.dp, 20.dp))
            .background(mainColor)
    ){
        Text(
            text = stringResource(R.string.stage_text).uppercase(),
            style = LocalTypography.current.titleMedium.copy(
                color = white,
                fontSize = 24.sp
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ScenePrev() {
    Scene()
}