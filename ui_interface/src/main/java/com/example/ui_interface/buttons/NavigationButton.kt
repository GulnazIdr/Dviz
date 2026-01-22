package com.example.ui_interface.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun NavigationButton(
    text: String,
    onAction: () -> Unit,
    enabled: Boolean,
    isCartButton: Boolean = false,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = mainColor,
            contentColor = white
        ),
        onClick = onAction,
        shape = RoundedCornerShape(13.dp),
        modifier = modifier.height(50.dp),
        enabled = enabled
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isCartButton) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "button icon",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = stringResource(R.string.cart_text),
                    style = LocalTypography.current.captionSemibold
                )
            } else {
                Text(
                    text = text,
                    style = LocalTypography.current.captionSemibold
                )
            }
        }
    }

}

@Preview
@Composable
private fun NavigationButtonPreview() {
    NavigationButton(
        stringResource(R.string.login_text),
        enabled = true,
        onAction = {}
    )
}