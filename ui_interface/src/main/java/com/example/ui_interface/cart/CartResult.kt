package com.example.ui_interface.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.darkGray
import com.example.ui_interface.theme.mainColor

@Composable
fun CartResult(
    onOrderButton: () -> Unit,
    productsPrice: Double,
    delivery: Double,
    modifier: Modifier = Modifier
) {
    val width = LocalWindowInfo.current.containerSize.width.dp.value
    val padding = with(LocalDensity.current) {20.dp.toPx()}
    val dash = with(LocalDensity.current) {6.dp.toPx()}

    Box(
        modifier = modifier.fillMaxSize().padding(bottom = 32.dp)
    ) {
        Column(
            Modifier
                .align(Alignment.BottomEnd)
                .background(Color.White)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(34.dp))

            Cost(
                text = stringResource(R.string.sum_text),
                price = productsPrice
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth().drawBehind{
                    drawLine(
                        start = Offset(x = 0f, y = 7f),
                        end = Offset(x = width-padding, y = 7f),
                        color = darkGray,
                        strokeWidth = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(dash, dash)    ,
                            phase = dash
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Cost(
                text = stringResource(R.string.result_text),
                price = productsPrice + delivery,
                colorName = black,
                colorPrice = mainColor
            )

            Spacer(modifier = Modifier.height(30.dp))

            NavigationButton(
                text = stringResource(R.string.confirm_text),
                onAction = {onOrderButton()}
            )
        }
    }
}

@Composable
fun Cost(
    text : String,
    price: Double,
    colorName: Color  = darkGray,
    colorPrice: Color = black,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = LocalTypography.current.titleMedium.copy(
                color = colorName
            )
        )

        Text(
            text = "$price₽",
            style = LocalTypography.current.titleMedium.copy(
                color = colorPrice
            )
        )
    }
}

@Preview
@Composable
private fun CartResultPreview() {
    CartResult(
        productsPrice = 300.0,
        delivery = 10.0,
        onOrderButton = {}
    )
}