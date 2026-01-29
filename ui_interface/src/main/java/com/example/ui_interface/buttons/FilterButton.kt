package com.example.ui_interface.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun FilterButton(
    text: String,
    onFilter: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialButtonColor = ButtonDefaults.buttonColors().copy(
        containerColor = white
    )

    val buttonColor = ButtonDefaults.buttonColors().copy(
        containerColor = mainColor
    )

    var isFocused by remember { mutableStateOf(false) }

    Button(
        colors = if(isFocused) buttonColor else initialButtonColor,
        onClick = {
            isFocused = !isFocused
            onFilter()
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(40.dp),
    ) {
        Text(
            text = text,
            style = LocalTypography.current.captionSemibold.copy(
                color =
                    if (isFocused) white
                    else black
            )
        )
    }

}