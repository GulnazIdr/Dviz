package com.example.ui_interface.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lightYellow
import com.example.ui_interface.theme.mainColor

@Composable
fun SuccessOrderModalWindow(
    navigateToStore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {navigateToStore()}
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .height(375.dp)
                .width(335.dp)
                .background(color = mainColor)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconBoxComponent(
                    id = R.drawable.success,
                    backgroundColor = lightYellow,
                    shape = CircleShape,
                    size = 134.dp,
                    iconSize = 86.dp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.success_text),
                    style = LocalTypography.current.titleMedium6
                )

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    text = stringResource(R.string.back_to_main_text),
                    onAction = { navigateToStore() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                    enabled = true
                )
            }
        }
    }
}

@Preview
@Composable
private fun SuccessOrderModalWindowPreview() {
    SuccessOrderModalWindow(
        navigateToStore = {}
    )
}