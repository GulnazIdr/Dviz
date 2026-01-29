package com.example.ui_interface

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ui_interface.components.BottomCurle
import com.example.ui_interface.theme.gray
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.white

@Composable
fun BottomBarMainPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(1f),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarIcon(
                R.drawable.home
            )
            BottomBarIcon(
                R.drawable.heart_empty
            )
        }
    }
    BottomCurle(
        color = lighterGray
    )
}

@Composable
fun BottomBarIcon(
    id: Int,
) {
    var focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused = interactionSource.collectIsFocusedAsState().value

    IconButton(
        onClick = {
            focusRequester.requestFocus()
        }
    ) {
        Icon(
            painter = painterResource(id),
            contentDescription = "home icon",
            modifier = Modifier
                .size(24.dp)
                .focusRequester(focusRequester)
                .focusable(interactionSource = interactionSource),
            tint =
                if(isFocused) white else gray
        )
    }
}