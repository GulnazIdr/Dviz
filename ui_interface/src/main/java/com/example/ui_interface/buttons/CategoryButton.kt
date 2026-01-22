package com.example.ui_interface.buttons

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun CategoryButton(
    text: String,
    onAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    Button(
        colors = ButtonDefaults.buttonColors().copy(
            contentColor = if(isFocused) white else mainColor,
            containerColor = if(isFocused) black else white
        ),
        onClick = {
            onAction()
            focusRequester.requestFocus()
        },
        shape = RoundedCornerShape(13.dp),
        modifier = modifier
            .height(40.dp)
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
    ) {
        Text(
            text = text,
            style = LocalTypography.current.captionSemibold
        )
    }
}

@Preview
@Composable
private fun CategoryButtonPrev() {
    CategoryButton(
        text = stringResource(R.string.login_text),
        onAction = {}
    )
}