package com.example.dviz.presentation.events.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.CircleLoading
import com.example.dviz.presentation.events.CardEvent
import com.example.dviz.presentation.events.EventUi

@Composable
fun EventRow(
    eventUiList: List<EventUi>,
    onCard:  (Int) -> Unit,
    isPageEnded: Boolean,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(eventUiList.size) { it ->
            CardEvent(
                event = eventUiList[it],
                onCard = { onCard(it) }
            )

            if (
                it >= eventUiList.size - 1 &&
                !isPageEnded
            ) {
                Spacer(modifier = Modifier.width(30.dp))
                CircleLoading( )
                loadMore()
            }

        }

    }
}