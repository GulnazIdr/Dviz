package com.example.ui_interface.places

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.theme.pastelGreen
import com.example.ui_interface.theme.white

@Composable
fun FourPlace(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = Modifier
            .height(83.dp)
            .width(83.dp)
            .background(white)
           // .rotate(180f)
            .drawWithCache{
                onDrawBehind {
                    rotate(degrees = 45F) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(35f, 35f),
                            color = pastelGreen,
                            size = Size(83.7f, 29.7f),
                            topLeft = Offset(70f, 200f)
                        )
                        drawRoundRect(
                            cornerRadius = CornerRadius(25f, 25f),
                            color = pastelGreen,
                            size = Size(62.1f, 8.1f),
                            topLeft = Offset(81f, 194f)
                        )
                    }

                    rotate(degrees = -45F) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(35f, 35f),
                            color = pastelGreen,
                            size = Size(83.7f, 29.7f),
                            topLeft = Offset(80.5f, 205f)
                        )
                        drawRoundRect(
                            cornerRadius = CornerRadius(25f, 25f),
                            color = pastelGreen,
                            size = Size(62.1f, 8.1f),
                            topLeft = Offset(92f, 199f)
                        )
                    }

                    rotate(degrees = 135F) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(35f, 35f),
                            color = pastelGreen,
                            size = Size(83.7f, 29.7f),
                            topLeft = Offset(77f, 199f)
                        )
                        drawRoundRect(
                            cornerRadius = CornerRadius(25f, 25f),
                            color = pastelGreen,
                            size = Size(62.1f, 8.1f),
                            topLeft = Offset(88f, 193f)
                        )
                    }

                    rotate(degrees = -135F) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(35f, 35f),
                            color = pastelGreen,
                            size = Size(83.7f, 29.7f),
                            topLeft = Offset(70f, 204f)
                        )
                        drawRoundRect(
                            cornerRadius = CornerRadius(25f, 25f),
                            color = pastelGreen,
                            size = Size(62.1f, 8.1f),
                            topLeft = Offset(81.5f, 198f)
                        )
                    }

                    rotate(degrees = 45F) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(32.4f, 32.4f),
                            color = pastelGreen,
                            size = Size(164f, 164f),
                            topLeft = Offset(35f, 30f)
                        )
                    }
                }
            }
    ) {}
}


@Preview
@Composable
private fun FourPlacePrev() {
    FourPlace()
}