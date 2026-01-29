package com.example.ui_interface.places

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.components.CardEvent
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.models.PlaceUi.RowUi.PriceColorUi
import com.example.ui_interface.theme.blue
import com.example.ui_interface.theme.brown
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.red

@Composable
fun PairedPlacesGrid(
    placeList: List<PlaceUi>,
    onClick: (PlaceUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(8),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(placeList.size) { index ->
            OnePlace(
                placeUi = placeList[index],
                onClick = { onClick(it) }
            )
            Log.d("sd", index.toString())

        }
    }
}
//
//@Preview
//@Composable
//private fun PairedPlacesGridPrev() {
//    PairedPlacesGrid(
//        onClick = {}
//    )
//}