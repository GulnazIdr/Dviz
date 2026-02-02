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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dviz.presentation.calendar.Calendar
import com.example.dviz.presentation.events.EventViewModel
import com.example.dviz.presentation.events.main.CityDropDownFilter
import com.example.dviz.presentation.events.main.EventRow
import com.example.dviz.presentation.events.main.MainTopAppBar
import com.example.ui_interface.BottomBarMainPage
import com.example.ui_interface.R
import com.example.ui_interface.components.CategoryList
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.darkBlack
import com.example.ui_interface.theme.lighterGray
import kotlin.collections.isNotEmpty

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onCategory: (String) -> Unit,
    onSearch: () -> Unit,
    onCard:  (Int) -> Unit,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
    eventViewModel: EventViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val cityList = eventViewModel.cityList.collectAsStateWithLifecycle()
    val isCityLoading = eventViewModel.isCityLoading.value

    val dates = calendarViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val isPageEnded = eventViewModel.isPageEnded.value
    val dateYear = dates.value.yearMonth

    val eventList = eventViewModel.eventList.collectAsStateWithLifecycle()
    val isEventLoading = eventViewModel.isEventLoading

    val filteredEventList = eventViewModel.filteredEventList.collectAsStateWithLifecycle()
    val isFiltering = eventViewModel.isFiltering

    val categoryList = eventViewModel.categoryList.collectAsStateWithLifecycle()
    val isCategoryLoading = eventViewModel.isCategoryLoading.value

    var dataList = if (isFiltering.value) filteredEventList else eventList

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
                Box() {
                    MainTopAppBar(
                        onSearch = {onSearch()},
                        onCity = {eventViewModel.setSelectedCity(it)},
                        onPrice = {},
                        onCart = {},
                        cityList = cityList.value,
                        isCityLoading = isCityLoading,
                        modifier = Modifier.zIndex(0f)
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Calendar(
                        yearMonth = dateYear,
                        onPreviousMonthButtonClicked = {
                            calendarViewModel.toMonth(
                                dateYear.minusMonths(1)
                            )
                        },
                        onNextMonthButtonClicked = {
                            calendarViewModel.toMonth(
                                dateYear.plusMonths(1)
                            )
                        },
                        dates = dates.value.dates,
                        onDateClickListener = { eventViewModel.setSelectedDay(it) },
                        modifier = Modifier.padding(horizontal = 40.dp).zIndex(1f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.category_text),
                    style = LocalTypography.current.captionSemibold3
                )

                Spacer(modifier = Modifier.height(16.dp))

                CategoryList(
                    onCategory = { onCategory(it) },
                    listOfButtons = categoryList.value,
                    isLoading = isCategoryLoading,
                    loading = {CircleLoading()}
                )

                Spacer(modifier = Modifier.height(28.dp))

                if (dataList.value.isEmpty())
                    CircleLoading()
                else {
                    val groupedListByCategory = dataList.value.groupBy {
                        it.categoryUi.name
                    }
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        groupedListByCategory.entries.forEachIndexed { index, (category, event) ->
                            items(1) {
                                Text(
                                    text = category,
                                    style = LocalTypography.current.captionSemibold3.copy(
                                        color = darkBlack
                                    ),
                                    modifier = Modifier.padding(bottom = 18.dp)
                                )
                                EventRow(
                                    eventUiList = event,
                                    onCard = { onCard(it) },
                                    isPageEnded = isPageEnded,
                                    loadMore = { eventViewModel.loadMoreEvents() }
                                )

                                if (index == groupedListByCategory.size - 1)
                                    Spacer(modifier = Modifier.height(144.dp))
                            }

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

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun HomeScreenPrev() {
//    HomeScreen(
//        onCategory = {},
//        onSearch = {}
//    )
//}