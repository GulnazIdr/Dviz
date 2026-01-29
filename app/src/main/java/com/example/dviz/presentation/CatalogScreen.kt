package com.example.dviz.presentation

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
import com.example.ui_interface.components.CardEvent
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
    navigateToFavorite:() -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var categoryId by remember { mutableIntStateOf(categoryId) }
    val categoryList = listOf<CategoryUi>(
        CategoryUi(1, "museum"),
        CategoryUi(2, "theater"),
        CategoryUi(3, "cinema"),
        CategoryUi(4, "concert"),
        CategoryUi(5, "meeting"),
        CategoryUi(6, "something else")
    )
    val data = listOf<EventUi>(
        EventUi(1,"Клод Моне. Магия воды и света", 1002.3, "",  categoryUi = categoryList[0]),
        EventUi(2,"Клод Моне. Магия воды и света", 1002.3, "", categoryUi = categoryList[0]),
        EventUi(3,"Клод Моне. Магия воды и света", 1002.3, "", categoryUi = categoryList[1]),
        EventUi(4,"Клод Моне. Магия воды и света", 1002.3, "", categoryUi = categoryList[2]),
        EventUi(5,"Клод Моне. Магия воды и света", 1002.3, "", categoryUi = categoryList[3])
    )

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
                    listOfButtons = categoryList
                )

                Spacer(modifier = Modifier.height(24.dp))

                val filteredEvents =
                    if (categoryId == 0) data
                    else data.filter { it.categoryUi.id == categoryId}


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    items(filteredEvents.size) {
                        CardEvent(
                            event = filteredEvents[it],
                            onCard = {onCard(it)}
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