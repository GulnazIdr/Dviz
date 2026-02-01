package com.example.dviz.presentation.events.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.events.CardEvent
import com.example.ui_interface.models.EventUi

@Composable
fun EventRow(
    eventUiList: List<EventUi>,
    onCard:  (Int) -> Unit,
    isPageEnded: Boolean,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(eventUiList.size) { it ->
            if (
                it >= eventUiList.size- 1 &&
                !isPageEnded
            ) {
                loadMore()
            }
            CardEvent(
                event = eventUiList[it],
                onCard = { onCard(it) }
            )
        }
    }
}