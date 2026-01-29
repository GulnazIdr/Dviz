package com.example.ui_interface.places

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.blue
import com.example.ui_interface.theme.brown
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.red

@Composable
fun PriceColorBoxRow(
    priceColorUiList: List<PlaceUi.RowUi.PriceColorUi>,
    modifier: Modifier = Modifier
) {
    LazyRow {
        items(priceColorUiList.size) {
            PriceColorBox(
                priceColorUi = priceColorUiList[it]
            )
        }
    }
}

@Preview
@Composable
private fun PriceColorBoxRowPrev() {
    PriceColorBoxRow(
        priceColorUiList = listOf(
            PlaceUi.RowUi.PriceColorUi(2200, mainColor),
            PlaceUi.RowUi.PriceColorUi(1200, red),
            PlaceUi.RowUi.PriceColorUi(2000, blue),
            PlaceUi.RowUi.PriceColorUi(800, brown)
        )
    )
}