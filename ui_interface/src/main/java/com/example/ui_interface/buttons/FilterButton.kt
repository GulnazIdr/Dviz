package com.example.ui_interface.buttons

import androidx.compose.foundation.layout.height
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
        containerColor = white,
        contentColor = black
    )

    val buttonColor = ButtonDefaults.buttonColors().copy(
        containerColor = white,
        contentColor = black
    )
    var currentButtonColor by remember { mutableStateOf(initialButtonColor) }

    Button(
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = mainColor,
            contentColor = white
        ),
        onClick = {
            currentButtonColor = buttonColor
            onFilter()
        },
        shape = RoundedCornerShape(13.dp),
        modifier = modifier.height(50.dp),
    ) {
        Text(
            text = text,
            style = LocalTypography.current.captionSemibold
        )
    }
}