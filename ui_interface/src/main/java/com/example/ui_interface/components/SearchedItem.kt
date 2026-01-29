package com.example.ui_interface.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.gray

@Composable
fun SearchedItem(
    searchedText: String,
    onSearchedText: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable(onClick = { })
    )   {
        Icon(
            painter = painterResource(id = R.drawable.clock),
            contentDescription = "clock icon",
            modifier = Modifier.size(22.dp),
            tint = gray
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = searchedText,
            style = LocalTypography.current.titleMedium2.copy(
                color = black
            )
        )
    }
}

@Preview
@Composable
private fun SearchedItemPrev() {
    SearchedItem(
        "seatched", {}
    )
}