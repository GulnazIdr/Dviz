package com.example.dviz.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.models.PlaceUi.RowUi.PriceColorUi
import com.example.ui_interface.places.ChosenPlaceDialogWindow
import com.example.ui_interface.places.EventPlaces
import com.example.ui_interface.places.PriceColorBoxRow
import com.example.ui_interface.theme.blue
import com.example.ui_interface.theme.brown
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.red
import com.example.ui_interface.top_bars.TopAppBar

@Composable
fun PlaceScreen(modifier: Modifier = Modifier) {
    var chosenPlaces by remember { mutableStateOf<List<PlaceUi>>(emptyList()) }

    val rowNumber = 4
    val colNumber = 4
    val colorList = listOf<Color>(red, blue, brown, mainColor)
    val rowList = mutableListOf<PlaceUi.RowUi>()
    val placeList = mutableListOf<PlaceUi>()
    val priceColorList = mutableListOf<PriceColorUi>()

    for (i in 1..rowNumber){
        var priceColorUi = PriceColorUi(1000, colorList[i-1])
        priceColorList.add(priceColorUi)
        rowList.add(PlaceUi.RowUi(i, priceColorUi))
    }

    for (rowNumber in 1..rowNumber) {
        for (colNumber in 1..colNumber * 2) {
            placeList.add(PlaceUi(colNumber, rowList[rowNumber-1]))
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    CommonScaffold(
        snackbarHostState = snackbarHostState,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(lighterGray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                TopAppBar(
                    onBack = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                PriceColorBoxRow(
                    priceColorUiList = priceColorList
                )

                Spacer(modifier = Modifier.height(10.dp))

                EventPlaces(
                    onClick = {
                        chosenPlaces += it
                    },
                    placeList = placeList
                )

                Spacer(modifier = Modifier.weight(1f))

                ChosenPlaceDialogWindow(
                    placeList = chosenPlaces
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlaceScreenPrev() {
    PlaceScreen()
}