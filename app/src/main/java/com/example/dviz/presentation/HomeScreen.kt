package com.example.dviz.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ui_interface.BottomBarMainPage
import com.example.ui_interface.R
import com.example.ui_interface.calendar.Calendar
import com.example.ui_interface.components.CategoryList
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.event.EventRow
import com.example.ui_interface.models.CalendarUi
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.EventUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.top_bars.MainTopAppBar
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onCategory: (id: Int) -> Unit,
    onSearch: () -> Unit,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val dates = calendarViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
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

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(lighterGray)
                .padding(padding)
        ) {
            Column(
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                MainTopAppBar(
                    onSearch = {onSearch()},
                    onCity = {},
                    onPrice = {},
                    onCart = {}
                )

                Spacer(modifier = Modifier.height(22.dp))

                Calendar(
                    yearMonth = dates.value.yearMonth,
                    onPreviousMonthButtonClicked = { calendarViewModel.toPreviousMonth(dates.value.yearMonth.minusMonths(1)) },
                    onNextMonthButtonClicked = {calendarViewModel.toNextMonth(dates.value.yearMonth.plusMonths(1))},
                    dates = dates.value.dates,
                    onDateClickListener = {},
                    modifier = Modifier.padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.category_text),
                    style = LocalTypography.current.captionSemibold3
                )

                Spacer(modifier = Modifier.height(16.dp))

                CategoryList(
                    onCategory = {onCategory(it)},
                    listOfButtons = categoryList
                )

                Spacer(modifier = Modifier.height(28.dp))

                val groupedListByCategory = data.groupBy { it.categoryUi }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    groupedListByCategory.forEach { (category, event) ->
                        items(
                            items = event,
                            key = { item -> item.id}
                        ) { item ->
                            EventRow(
                                eventUiList = event
                            )
                        }
                    }
                }
            }

            BottomBarMainPage(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 26.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(
        onCategory = {},
        onSearch = {}
    )
}