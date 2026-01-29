package com.example.ui_interface.top_bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.buttons.CustomIconButton
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.lightgray

@Composable
fun TopAppBar(
    onBack: () -> Unit = {},
    title: String = "",
    iconId: Int? = null,
    onRightIcon: () -> Unit = {},
    isLogin: Boolean = false,
    isRegistrationScreen: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (!isLogin)
            CustomIconButton(
                iconId = R.drawable.arrow,
                modifier = Modifier
                    .clickable(onClick = { onBack() })
                    .align(Alignment.TopStart),
                backgroundColor = lighterGray,
                size = 44.dp,
                iconSize = 22.dp
            )

        if(!isRegistrationScreen) {
            Text(
                text = title,
                style = LocalTypography.current.captionSemibold3,
                modifier = Modifier.align(Alignment.Center)
            )

            if(iconId != null)
                CustomIconButton(
                    iconId = iconId,
                    modifier = Modifier
                        .clickable(onClick = { onRightIcon() })
                        .align(Alignment.TopEnd),
                    backgroundColor = lightgray
                )
        }
    }
}

@Preview
@Composable
private fun TopAppBarPrev() {
    TopAppBar(
        onBack = {}
    )
}