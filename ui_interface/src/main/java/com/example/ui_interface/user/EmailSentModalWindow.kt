package com.example.ui_interface.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ui_interface.R
import com.example.ui_interface.components.IconBoxComponent
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white
import kotlinx.coroutines.delay

@Composable
fun EmailSentModalWindow(
    email: String,
    navigateToOtp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val onDismiss = LaunchedEffect(Unit) {
        delay(5000L)
    }
    navigateToOtp(email)

    Dialog(onDismissRequest = {onDismiss}) {
        Box(
            modifier = modifier
                .height(196.dp)
                .width(335.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconBoxComponent(
                    id = R.drawable.email,
                    backgroundColor = mainColor,
                    size = 44.dp,
                    shape = CircleShape,
                    iconSize = 24.dp,
                    tint = white
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.check_email_text),
                    style = LocalTypography.current.titleBold2
                )

                Text(
                    text = stringResource(R.string.check_email_descr),
                    style = LocalTypography.current.headLineRegular2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun EmailSentModalWindowPreview() {
    EmailSentModalWindow(email = "", navigateToOtp = {})
}