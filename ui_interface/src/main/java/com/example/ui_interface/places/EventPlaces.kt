package com.example.ui_interface.places

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.blue
import com.example.ui_interface.theme.brown
import com.example.ui_interface.theme.lightYellow
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.red

@Composable
fun EventPlaces(
    placeList: List<PlaceUi>,
    onClick: (PlaceUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(lightYellow)
    ) {
        Column(
            modifier = Modifier.padding(
                start = 17.dp, end = 17.dp, top = 73.dp, bottom = 50.dp
            )
        ) {
            PairedPlacesGrid(
                onClick = {onClick(it)},
                placeList = placeList
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                FourPlace()

                Scene(modifier = Modifier.offset(0.dp, 30.dp))

                FourPlace()
            }


        }
    }
}

@Preview
@Composable
private fun EventPlacesPrev() {
    EventPlaces(
        onClick = {},
        placeList = listOf(
            PlaceUi(1, PlaceUi.RowUi(1, PlaceUi.RowUi.PriceColorUi(100, mainColor))),
            PlaceUi(2, PlaceUi.RowUi(2, PlaceUi.RowUi.PriceColorUi(200, red))),
            PlaceUi(3, PlaceUi.RowUi(3, PlaceUi.RowUi.PriceColorUi(300, blue))),
            PlaceUi(4, PlaceUi.RowUi(4, PlaceUi.RowUi.PriceColorUi(400, brown))),
            PlaceUi(5, PlaceUi.RowUi(1, PlaceUi.RowUi.PriceColorUi(100, mainColor))),
            PlaceUi(6, PlaceUi.RowUi(2, PlaceUi.RowUi.PriceColorUi(200, red))),
            PlaceUi(7, PlaceUi.RowUi(3, PlaceUi.RowUi.PriceColorUi(300, blue))),
            PlaceUi(8, PlaceUi.RowUi(4, PlaceUi.RowUi.PriceColorUi(400, brown))),
            PlaceUi(9, PlaceUi.RowUi(1, PlaceUi.RowUi.PriceColorUi(100, mainColor))),
            PlaceUi(10, PlaceUi.RowUi(2, PlaceUi.RowUi.PriceColorUi(200, red))),
            PlaceUi(11, PlaceUi.RowUi(3, PlaceUi.RowUi.PriceColorUi(300, blue))),
            PlaceUi(12, PlaceUi.RowUi(4, PlaceUi.RowUi.PriceColorUi(400, brown))),
        )
    )
}