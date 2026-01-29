package com.example.ui_interface.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lightgray

@Composable
fun BottomRegistrTextNav(
    question: String,
    link: String,
    onNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(bottom = 47.dp)
        ) {

            Text(
                text = question,
                style = LocalTypography.current.titleMedium.copy(
                    color = lightgray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { onNavigateTo() }) {
                Text(
                    text = link,
                    style = LocalTypography.current.titleMedium
                )
            }
        }
    }
}
@Composable
fun RegistrTitle(
    registrTitle: String,
    registrDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 11.dp, bottom = 30.dp)
    ) {
        Text(
            text = registrTitle,
            style = LocalTypography.current.titleBold,
            textAlign = TextAlign.Center
        )

        Text(
            text = registrDescription,
            style = LocalTypography.current.headLineRegular2,
            textAlign = TextAlign.Center,
            modifier = modifier
                .width(315.dp)
                .padding(top = 8.dp)
        )
    }
}