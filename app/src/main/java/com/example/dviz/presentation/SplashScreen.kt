package com.example.dviz.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onDelayFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1000)
        onDelayFinished()
    }

    Box(
        modifier
            .fillMaxSize()
            .background(mainColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = stringResource(R.string.app_name_rus).uppercase(),
            style = LocalTypography.current.titleBold.copy(
                color = white,
                fontSize = 65.sp
            )
        )
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen(onDelayFinished = {})
}