package com.example.ui_interface.places

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.models.PlaceUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.mainColor
import com.example.ui_interface.theme.white

@Composable
fun PriceColorBox(
    priceColorUi: PlaceUi.RowUi.PriceColorUi,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .clip(RoundedCornerShape(7.dp))
            .background(white)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier
                .size(15.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(priceColorUi.color)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "${priceColorUi.price}",
            style = LocalTypography.current.titleMedium.copy(
                color = black
            )
        )
    }
}

@Preview
@Composable
private fun PriceColorBoxPrev() {
    PriceColorBox(
        priceColorUi = PlaceUi.RowUi.PriceColorUi(2200, mainColor)
    )
}