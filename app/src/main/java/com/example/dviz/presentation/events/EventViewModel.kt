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
import com.example.dviz.domain.models.Location
import com.example.dviz.domain.models.Places
import com.example.dviz.presentation.calendar.CalendarDataSource
import com.example.dviz.presentation.mappers.toCategoryUi
import com.example.dviz.presentation.mappers.toEventUi
import com.example.dviz.presentation.mappers.toFetchedResultEvUi
import com.example.dviz.presentation.mappers.toLocationUi
import com.example.ui_interface.models.CategoryUi
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
    private var isEventPageEnded = mutableStateOf(false)
    private var isPlacePageEnded = mutableStateOf(false)
    private var isMoviePageEnded = mutableStateOf(false)
    private var eventCurrentPage = 1
    private var placeCurrentPage = 1
    private var movieCurrentPage = 1
    var  dayIndex = 0
    var monthIndex = 0
    private var eventStartingPrice = 200
    @RequiresApi(Build.VERSION_CODES.O)
    private var eventMonths = listOf(YearMonth.now(),YearMonth.now().plusMonths(1))
    private val _isEventLoading = mutableStateOf(false)
    var isEventLoading: State<Boolean> = _isEventLoading
    private val _isCityLoading = mutableStateOf(true)
    var isCityLoading: State<Boolean> = _isCityLoading
    private val _isFiltering = mutableStateOf(false)
    var isFiltering: State<Boolean> = _isFiltering
    private val _isCategoryLoading = mutableStateOf(false)
    var isCategoryLoading: State<Boolean> = _isCategoryLoading
    private val _filteredEventList = MutableStateFlow<List<EventUi>>(listOf())
    val filteredEventList = _filteredEventList.asStateFlow()
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

    private val _cityList = MutableStateFlow<List<LocationUi>>(listOf())
    @RequiresApi(Build.VERSION_CODES.O)
    val cityList = _cityList
        .onStart {
            loadCityList()
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
                        info.results.mapIndexed {index,place -> place
                                .toEventUi()
                                .apply {
                                    dateTime = EventDateTime(
                                            eventMonths[monthIndex],
                                            calendarDataSource.
                                            getDates(eventMonths[monthIndex])[dayIndex]
                                                .dayOfMonth.toInt()
                                    )
                                    price =
                                        if (index % 2 == 0) "$eventStartingPrice₽"
                                        else price

                                    eventStartingPrice+=20
                                    monthIndex = if (monthIndex == 1) 0 else 1
                                    dayIndex = if (dayIndex < 33) dayIndex + 1 else 0
                            }
                        }
                    }
                    _eventList.value += fetched.distinctBy { it.title }
                }
                if (!isEventPageEnded.value) eventCurrentPage++
                if (!isPlacePageEnded.value) placeCurrentPage++
                if (!isMoviePageEnded.value) movieCurrentPage++
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

    fun setSelectedDay(date: EventDateTime){
        _isFiltering.value = true
        _filteredEventList.value = _eventList.value.filter {
            it.dateTime.date == date.date && it.dateTime.yearMonth == date.yearMonth
        }
        _isEventLoading.value = false
    }

    private fun loadCityList(){
        _isCityLoading.value = true
        viewModelScope.launch {
            when (val res = eventRepository.getLocations()) {
                is FetchResult.Success<List<Location>> -> {
                    _cityList.value = res.data!!.map { it.toLocationUi() }
                    Log.d("cici2", "${_cityList.value.size}")
                    _isCityLoading.value = false
                }

                is FetchResult.Error<List<Location>> -> {
                    _isCityLoading.value = false
                }

                is FetchResult.Initial<List<Location>> -> {}
            }
        }
    }

    fun setSelectedCity(id: String){
        _isFiltering.value = true
        _filteredEventList.value = _eventList.value.filter {
            it.city.slug == id
        }
        _isEventLoading.value = false
    }
}