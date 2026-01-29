package com.example.ui_interface.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.red

@Composable
fun OtpComponent(
    value: String,
    onValueChanged: (Pair<Int, String>) -> Unit,
    index: Int,
    inputStateEnabled: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    Box(
        modifier = modifier
            .width(46.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(lighterGray)
            .border(2.dp,
                if (isError) red
                else if (isFocused) mainColor
                else lighterGray,
                RoundedCornerShape(12.dp))
            .height(99.dp)
            .clickable(onClick = {focusRequester.requestFocus()})
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
    ) {
        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            BasicTextField(
                value = value,
                onValueChange = {onValueChanged(Pair(index, it))},
                textStyle = LocalTypography.current.captionSemibold3.copy(
                    textAlign = TextAlign.Center
                ),
                enabled = inputStateEnabled,
                interactionSource = interactionSource
            )
        }
    }
}

//@Preview
//@Composable
//private fun OtpComponentPreview() {
//    OtpComponent(
//        index = 0,
//        value = "",
//        onValueChanged = {}
//    )
//}