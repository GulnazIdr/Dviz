package com.example.ui_interface.places

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun OnePlace(
    placeUi: PlaceUi,
    onClick: (PlaceUi) -> Unit,
    modifier: Modifier = Modifier
) {
    val style = LocalTypography.current.titleMedium3.copy(
        color = white
    )
    val textMeasurer = rememberTextMeasurer()
    var currentColor by remember { mutableStateOf(placeUi.rowInfo.priceColorUi.color) }

    Canvas(
        modifier = Modifier
            .height(37.dp)
            //   .width(35.dp)
            .background(white)
            .clickable(onClick = {
                onClick(placeUi)
                currentColor = lighterGray
            })
            .drawWithCache {
                val placeResult = textMeasurer.measure(
                    text = "${placeUi.number}",
                    style = style,
                )

                onDrawBehind {
                    drawRoundRect(
                        cornerRadius = CornerRadius(25f, 25f),
                        color = currentColor,
                        size = Size(94.5f, 67.5f)
                    )

                    drawRoundRect(
                        cornerRadius = CornerRadius(25f, 25f),
                        color = currentColor,
                        size = Size(62.1f, 8.1f),
                        topLeft = Offset(17f, 66f)
                    )

                    drawRoundRect(
                        cornerRadius = CornerRadius(35f, 35f),
                        color = currentColor,
                        size = Size(83.7f, 29.7f),
                        topLeft = Offset(6.5f, 72f)
                    )
                    drawText(
                        textLayoutResult = placeResult,
                        topLeft = Offset(35f, 28f)
                    )
                }
            }
    ) {}
}


@Preview
@Composable
private fun OnePlacePrev() {
    OnePlace(
        placeUi = PlaceUi(1, PlaceUi.RowUi(1, PlaceUi.RowUi.PriceColorUi(1200, mainColor))),
        onClick = {}
    )
}