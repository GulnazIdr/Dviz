package com.example.dviz.presentation.events.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.dviz.domain.FetchResult
import com.example.dviz.presentation.CircleLoading
import com.example.dviz.presentation.events.EventViewModel
import com.example.ui_interface.components.CommonScaffold
import com.example.dviz.presentation.events.EventUi
import com.example.ui_interface.theme.white
import com.example.ui_interface.top_bars.TopAppBar

@Composable
fun EventDetails(
    onBack: () -> Unit,
    eventId: Int,
    eventViewModel: EventViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        eventViewModel.getPlaceById(eventId)
    }

    val fetchedEventResult = eventViewModel.fetchedEventStateFlow.collectAsStateWithLifecycle()
    val result = fetchedEventResult.value
    val snackBarHostState = remember { SnackbarHostState() }

    CommonScaffold(
        snackbarHostState = snackBarHostState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(white)
        ) {
            when(result){
                is FetchResult.Success<EventUi> -> {
                    Column() {
                        TopAppBar(
                            onBack = { onBack() }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(result.data!!.images) {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "${result.data.title} image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)),
                                    onError = {}
                                )
                            }
                        }

                        Text(
                            text = result.data!!.title
                        )

                        Text(
                            text =  result.data!!.description
                        )

                        Text(
                            text = "${result.data.price}"
                        )

                        Text(
                            text = "${result.data.dateTime}"
                        )

                        Text(
                            text = result.data.address
                        )

                        Text(
                            text = "${result.data.ageLimit}+"
                        )
                    }
                }
                is FetchResult.Error<EventUi> -> {}
                is FetchResult.Initial<EventUi> -> {
                    CircleLoading()
                }
            }
        }
    }
}

@Preview
@Composable
private fun EventDetailsPrev() {
    EventDetails(
        onBack = {},
        eventId = 0
    )
}