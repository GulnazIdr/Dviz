package com.example.dviz.presentation.events.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.dviz.presentation.events.LocationUi
import com.example.dviz.ui.theme.DvizTheme
import com.example.ui_interface.buttons.FilterButton

@Composable
fun CityDropDownFilter(
    cityList: List<LocationUi>,
    onCity: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var currentCity by remember { mutableStateOf(
        if(cityList.isNotEmpty()) cityList[0].name else ""
    ) }

    if(isExpanded)
        Column(
            modifier = Modifier
        ) {
            cityList.forEach {
                FilterButton(
                    text = it.name,
                    onFilter = {
                        onCity(it.id)
                        currentCity = it.name
                        isExpanded = !isExpanded
                    }
                )
            }
        }
    else{
        FilterButton(
            text = currentCity.ifEmpty {
                stringResource(com.example.ui_interface.R.string.city_text) },
            onFilter = {isExpanded = true},
        )
    }
}
