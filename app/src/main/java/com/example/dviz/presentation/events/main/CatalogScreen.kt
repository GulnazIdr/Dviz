package com.example.dviz.presentation.events.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.dviz.presentation.events.CardEvent
import com.example.dviz.presentation.events.EventViewModel
import com.example.ui_interface.components.CategoryList
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.EventUi
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.top_bars.TopAppBar

@Composable
fun CatalogScreen(
    categoryId: Int,
    onCard: (id: Int) ->  Unit,
    onBack: () -> Unit,
    eventViewModel: EventViewModel = hiltViewModel(),
    navigateToFavorite:() -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: finish here
    val snackbarHostState = remember { SnackbarHostState() }

    var categoryId by remember { mutableIntStateOf(categoryId) }
    val categoryList = listOf<CategoryUi>()
    val data = listOf<EventUi>()

    BackHandler { onBack() }

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) { padding ->
        Box(
            modifier = modifier
                .background(lighterGray)
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                TopAppBar(
                    onBack = {onBack()}
                )

                CategoryList(
                    onCategory = {},
                    listOfButtons = categoryList,
                    isLoading = false,
                    loading = {}
                )

                Spacer(modifier = Modifier.height(24.dp))

                val filteredEvents = data
//                    if (categoryId == 0) data
//                    else data.filter {
//                        it.categoryUi.id == categoryId
//                    }


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    items(filteredEvents.size) {
                        CardEvent(
                            event = filteredEvents[it],
                            onCard = { onCard(it) }
                        )
                    }
                }


            }
        }
    }

}

@Preview
@Composable
private fun CatalogScreenPreview() {
    CatalogScreen(
        categoryId =  1,
        onBack = {},
        onCard = {},
        navigateToFavorite = {}
    )
}