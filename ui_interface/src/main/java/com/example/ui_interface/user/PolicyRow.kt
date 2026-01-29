package com.example.ui_interface.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_interface.R
import com.example.ui_interface.components.IconBoxComponent
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.darkGray
import com.example.ui_interface.theme.lighterGray

@Composable
fun PolicyRow(
    onPdfRead: () -> Unit,
    isIconVisible: Boolean = false,
    toggleAgreement: () -> Unit,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Column() {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconBoxComponent(
                id = if (isIconVisible) R.drawable.secure else null,
                backgroundColor = lighterGray,
                size = 18.dp,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.clickable(onClick = { toggleAgreement() })
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.privacy_policy_text),
                style = LocalTypography.current.titleMedium3.copy(
                    textDecoration = TextDecoration.Underline,
                    color = darkGray
                ),
                modifier = Modifier.clickable(onClick = {
                    onPdfRead()
                })
            )
        }
        if (errorMessage.isNotEmpty())
            Text(
                text = errorMessage,
                style = LocalTypography.current.titleMediumError,
                modifier = modifier
                    .padding(top = 10.dp)
                    .align(Alignment.End)
            )
    }
}