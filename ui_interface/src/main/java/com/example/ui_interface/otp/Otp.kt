package com.example.ui_interface.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Otp(
    value: String,
    onValueChanged: (Pair<Int, String>) -> Unit,
    onResend: () -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    var inputStateEnabled by remember { mutableStateOf(true) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(6) { index ->
                OtpComponent(
                    index = index,
                    inputStateEnabled = inputStateEnabled,
                    value = value,
                    onValueChanged = {onValueChanged(Pair(it.first, it.second))},
                    isError = isError
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        OtpTimer(
            onFinished = {
                inputStateEnabled = !it
                if (it) onResend()
            }
        )
    }
}
//
//@Preview
//@Composable
//private fun OtpRowPr() {
//    Otp(
//        value = "",
//        onValueChanged = {}
//    )
//}