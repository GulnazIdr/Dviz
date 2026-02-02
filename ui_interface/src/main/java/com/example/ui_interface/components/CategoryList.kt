package com.example.ui_interface.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui_interface.buttons.CategoryButton
import com.example.ui_interface.models.CategoryUi

@Composable
fun CategoryList(
    onCategory: (String) -> Unit,
    listOfButtons: List<CategoryUi>,
    isLoading: Boolean = false,
    loading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        if (!isLoading) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listOfButtons.size) { button ->
                    CategoryButton(
                        text = listOfButtons[button].name,
                        onAction = { onCategory(listOfButtons[button].name) },
                    )
                }
            }
        }else loading()
    }
}
//
//@Preview
//@Composable
//private fun CategoryListPreview() {
//    CategoryList(
//        onCategory = {},
//        listOf<CategoryUi>(CategoryUi(0, "some", ), CategoryUi(1, "some2", ))
//    )
//}