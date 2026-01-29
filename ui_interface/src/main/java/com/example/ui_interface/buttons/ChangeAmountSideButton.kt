package com.example.ui_interface.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography

@Composable
fun ChangeAmountSideButton(
    amount: Int,
    onAddToCart: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    SideButton {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
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