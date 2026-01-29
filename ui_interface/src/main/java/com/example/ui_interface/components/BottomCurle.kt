package com.example.ui_interface.components

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun BottomCurle(
    color: Color = white
) {
    val path = path(LocalDensity.current)

    Column {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(144.dp)
                .fillMaxWidth()
                .background(mainColor)
        ) {
            Box(
                modifier = Modifier
                    .zIndex(0f)
                    .fillMaxSize()
                    .drawWithContent() {
                        drawPath(
                            path = path,
                            color = color,
                        )
                    }
            )
        }
    }
}

fun path(density: Density): Path{
    val width = with(density) { Resources.getSystem().configuration.screenWidthDp.dp.toPx()}
    val height = 173f

    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(width, 0f)
        lineTo(width, 0f)
        quadraticTo(width / 2, height + 250f, 0f, 0f)
        close()
    }

    return path
}

@Preview
@Composable
private fun BottomCurlePrev() {
    BottomCurle()
}