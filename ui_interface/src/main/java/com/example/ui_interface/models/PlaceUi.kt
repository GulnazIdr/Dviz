package com.example.ui_interface.models

import androidx.compose.ui.graphics.Color

data class PlaceUi(
    val number: Int,
    val rowInfo: RowUi,
    val isTaken: Boolean = false
){
    data class RowUi(
        val rowNumber: Int,
        val priceColorUi: PriceColorUi,
    ) {
        data class PriceColorUi(
            val price: Int,
            val color: Color
        )
    }
}

