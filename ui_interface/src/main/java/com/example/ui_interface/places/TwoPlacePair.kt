//package com.example.ui_interface.places
//
//import androidx.compose.foundation.layout.Row
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.ui_interface.theme.brown
//
//@Composable
//fun TwoPlacePair(
//    firstPlaceNumber: Int,
//    secondPlaceNumber: Int,
//    placeColor: Color,
//    modifier: Modifier = Modifier
//) {
//    Row{
//        OnePlace(
//            placeNumber = firstPlaceNumber,
//            placeColor = placeColor
//        )
//
//        OnePlace(
//            placeNumber = secondPlaceNumber,
//            placeColor = placeColor
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun TwoPlacePairPrev() {
//    TwoPlacePair(
//        1, 2, brown
//    )
//}