package com.example.dviz.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun AutoResizedText(
    text: String,
    initialStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    var resizedTextStyle by remember { mutableStateOf(initialStyle) }

    Text(
        text = text,
        style = resizedTextStyle,
        softWrap = false,
        onTextLayout = {result ->
            if (result.didOverflowWidth){
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            }
        }
    )
}