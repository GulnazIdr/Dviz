package com.example.ui_interface.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_interface.R
import com.example.ui_interface.otp.OtpTImerCountDown.Companion.timer
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lightSubGreyColor

@Composable
fun OtpTimer(
    onFinished: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputStateEnabled by remember { mutableStateOf(true) }
    var timerCount by rememberSaveable { mutableIntStateOf(30) }
    var startTimer by rememberSaveable { mutableStateOf(true)}

    if (startTimer) {
        LaunchedEffect(Unit) {
            timer(60).collect { timestate ->
                timerCount = timestate
            }
        }
    }

    if(timerCount == 0) {
        startTimer = false
        onFinished(true)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.resend_otp_text),
            style = LocalTypography.current.headLineRegular.copy(
                color = lightSubGreyColor
            ),
            modifier = Modifier.clickable(
                enabled = !inputStateEnabled,
                onClick = {}
            )
        )

        AnimatedVisibility(
            visible = inputStateEnabled,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = timerCount.toString(),
                style = LocalTypography.current.headLineRegular.copy(
                    color = lightSubGreyColor
                )
            )
        }
    }
}

@Preview
@Composable
private fun OtpTimerPrev() {
    OtpTimer(
        onFinished = {}
    )
}