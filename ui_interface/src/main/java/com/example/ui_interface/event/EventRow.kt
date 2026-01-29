package com.example.ui_interface.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui_interface.components.CardEvent
import com.example.ui_interface.models.EventUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.darkBlack

@Composable
fun EventRow(
    eventUiList: List<EventUi>,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = eventUiList[0].categoryUi.name,
            style = LocalTypography.current.captionSemibold3.copy(
                color = darkBlack
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(eventUiList.size) {
                CardEvent(
                    event = eventUiList[it],
                    onCard = {}
                )
            }
        }
    }
}