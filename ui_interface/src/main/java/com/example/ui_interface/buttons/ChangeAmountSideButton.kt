package com.example.ui_interface.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.mainColor

@Composable
fun ChangeAmountSideButton(
    amount: Int,
    onAddToCart: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = 20.dp)
            .height(221.dp)
            .width(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(mainColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {onAddToCart()}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_increment),
                    contentDescription = "increment icon",
                    tint = Color.White
                )
            }

            Text(
                text = amount.toString(),
                style = LocalTypography.current.headLineRegular3
            )

            IconButton(onClick = {onDecrement()}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_decrement),
                    contentDescription = "increment icon",
                    tint = Color.White
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChangeAmountSideButtonPreview() {
    ChangeAmountSideButton(amount = 1, {}, {})
}