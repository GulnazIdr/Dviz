package com.example.ui_interface.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.buttons.CategoryButton
import com.example.ui_interface.models.CategoryUi

@Composable
fun CategoryList(
    onCategory: (id: Int) -> Unit,
    listOfButtons: List<CategoryUi>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        if (listOfButtons.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listOfButtons.size) { button ->
                    CategoryButton(
                        text = listOfButtons[button].name,
                        onAction = { onCategory(button) },
                    )
                }
            }
        }else {
            CategoryButton(
                text = stringResource(R.string.all_text),
                onAction = { onCategory(-1) },
            )
        }
    }
}

@Preview
@Composable
private fun CategoryListPreview() {
    CategoryList(
        onCategory = {},
        listOf<CategoryUi>(CategoryUi(0, "some", ), CategoryUi(1, "some2", ))
    )
}