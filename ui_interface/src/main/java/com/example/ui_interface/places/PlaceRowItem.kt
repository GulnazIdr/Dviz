package com.example.ui_interface.places

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.components.IconBoxComponent
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.grayLight
import com.example.ui_interface.theme.lighterGray
import com.example.ui_interface.theme.mainColor

@Composable
fun PlaceRowItem(
    placeUi: PlaceUi,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(lighterGray)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(
                    R.string.place_row_text,
                    placeUi.number,
                    placeUi.rowInfo.rowNumber
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            IconBoxComponent(
                id = R.drawable.ic_increment,
                backgroundColor = grayLight,
                size = 20.dp,
                iconSize = 16.dp,
                shape = CircleShape,
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}

@Preview
@Composable
private fun PlaceRowItemPrev() {
    PlaceRowItem(
        placeUi = PlaceUi(1, PlaceUi.RowUi(2, PlaceUi.RowUi.PriceColorUi(100, mainColor)))
    )
}