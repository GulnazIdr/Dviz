package com.example.dviz.presentation.events

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.event.EventRepository
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Places
import com.example.dviz.presentation.mappers.toCategoryUi
import com.example.dviz.presentation.mappers.toEventUi
import com.example.dviz.presentation.mappers.toFetchedResultEvUi
import com.example.ui_interface.calendar.CalendarDataSource
import com.example.ui_interface.models.CalendarUi
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.EventUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val calendarDataSource: CalendarDataSource
): ViewModel() {
    var isPageEnded = mutableStateOf(false)
    private var collectedDays = listOf<CalendarUi.Date>()
    private var isEventPageEnded = mutableStateOf(false)
    private var isPlacePageEnded = mutableStateOf(false)
    private var isMoviePageEnded = mutableStateOf(false)
    private var eventCurrentPage = 0
    private var placeCurrentPage = 0
    private var movieCurrentPage = 0
    @RequiresApi(Build.VERSION_CODES.O)
    private var eventMonths = listOf(YearMonth.now(),YearMonth.now().plusMonths(1))
    private val _isEventLoading = mutableStateOf(false)
    var isEventLoading: State<Boolean> = _isEventLoading
    private val _isCategoryLoading = mutableStateOf(false)
    var isCategoryLoading: State<Boolean> = _isCategoryLoading
    private val _eventList = MutableStateFlow<List<EventUi>>(listOf())
    @RequiresApi(Build.VERSION_CODES.O)
    val eventList = _eventList
        .onStart {
            loadEvents()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            listOf()
        )

    private val _fetchedEventStateFlow = MutableStateFlow<FetchResult<
            EventUi>>(FetchResult.Initial())
    val fetchedEventStateFlow = _fetchedEventStateFlow.asStateFlow()
    private val _categoryList = MutableStateFlow<List<CategoryUi>>(listOf())
    val categoryList = _categoryList
        .onStart { loadCategories() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            listOf()
        )

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadMoreEvents(){
        viewModelScope.launch { loadEvents() }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun loadEvents() {
        _isEventLoading.value = true
        when (val resList = eventRepository.fetchEventList(
            eventCurrentPage, placeCurrentPage, movieCurrentPage
        )) {
            is FetchResult.Success<List<Places>> -> {
                if (resList.data!!.size == 3){

                    isEventPageEnded.value = eventCurrentPage * 20 >= resList.data[0].count
                    isPlacePageEnded.value = placeCurrentPage * 20 >= resList.data[1].count
                    isMoviePageEnded.value = movieCurrentPage * 20 >= resList.data[2].count
                    isPageEnded.value = isEventPageEnded.value &&
                            isPlacePageEnded.value && isMoviePageEnded.value

                    val fetched = resList.data.flatMap { info ->
                        info.results.map { it.toEventUi() }
                    }.apply {
                        eventMonths.forEach { month ->
                            forEach { it.dateTime = CalendarUi(
                                month,
                                calendarDataSource.getDates(month)
                            )}
                        }
                    }
                    _eventList.value += fetched
                }
                if (!isEventPageEnded.value) eventCurrentPage++
                if (!isPlacePageEnded.value) placeCurrentPage++
                if (!isMoviePageEnded.value) movieCurrentPage++
                Log.d("log","$eventCurrentPage $placeCurrentPage $movieCurrentPage")
                _isEventLoading.value = false
            }

            is FetchResult.Error<List<Places>> -> {
                _isEventLoading.value = false
            }

            is FetchResult.Initial<List<Places>> -> {}
        }
    }

    private fun loadCategories() {
        _isCategoryLoading.value = true
        viewModelScope.launch {
            when (val res = eventRepository.fetchCategoryList()) {
                is FetchResult.Success<List<Category>> -> {
                    _categoryList.value = res.data!!.map { it.toCategoryUi() }
                    _isCategoryLoading.value = false
                }

                is FetchResult.Error<List<Category>> -> {
                    _isCategoryLoading.value = false
                }

                is FetchResult.Initial<List<Category>> -> {}
            }
        }
    }

    fun getPlaceById(id: Int){
        viewModelScope.launch {
            _fetchedEventStateFlow.value = eventRepository.getPlaceById(id).toFetchedResultEvUi()
        }
    }

    fun setSelectedDays(){
        
    }
}