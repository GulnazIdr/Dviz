package com.example.ui_interface.places

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.brown
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun ChosenPlaceDialogWindow(
    placeList: List<PlaceUi>,
    modifier: Modifier = Modifier
) {
    val ticketSummary = pluralStringResource(
        id = R.plurals.ticket_plural_summary,
        count = placeList.size,
        placeList.size,
        placeList.sumOf { it.rowInfo.priceColorUi.price }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
            .background(white)
            .padding(horizontal = 20.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = ticketSummary
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                items(placeList.size) {
                    PlaceRowItem(
                        placeUi = placeList[it]
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            NavigationButton(
                text = stringResource(R.string.confirm_text),
                onAction = {},
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ChosenPlaceDialogWindowPrev() {
    ChosenPlaceDialogWindow(
        placeList =  listOf(
                PlaceUi(1, PlaceUi.RowUi(2, PlaceUi.RowUi.PriceColorUi(100, mainColor))),
                PlaceUi(2, PlaceUi.RowUi(3, PlaceUi.RowUi.PriceColorUi(200, brown)))
            )
    )
}