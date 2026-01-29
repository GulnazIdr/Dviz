package com.example.dviz.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.components.SearchedItem
import com.example.ui_interface.inputs.SearchField
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.top_bars.TopAppBar

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var input by rememberSaveable { mutableStateOf("s") }
    val snackbarHostState = remember { SnackbarHostState() }
    val searchHistoryList = listOf<String>("something", "something1")
    var isSearching by remember { mutableStateOf(false) }

    BackHandler { onBackClick()}

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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                TopAppBar(
                    title = stringResource(R.string.search_text),
                    onBack = {onBackClick()}
                )

                Spacer(modifier = modifier.height(26.dp))

                SearchField(
                    text = input,
                    onValueChanged = {input = it},
                    onSearchIcon = {},
                    onSearch = {}
                )

                Spacer(modifier = Modifier.height(29.dp))

                if(!isSearching) LazyColumn {
                    items(searchHistoryList.size) { index ->
                        SearchedItem(
                            searchedText = searchHistoryList[index],
                            onSearchedText = {}
                        )
                    }
                }


            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPrev() {
    SearchScreen(onBackClick = {})
}