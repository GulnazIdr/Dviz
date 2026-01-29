package com.example.ui_interface.top_bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.buttons.CustomIconButton
import com.example.ui_interface.buttons.FilterButton

@Composable
fun MainTopAppBar(
    onSearch: () -> Unit,
    onCity: () -> Unit,
    onPrice: () -> Unit,
    onCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomIconButton(
            iconId = R.drawable.ic_search,
            modifier = Modifier.clickable(onClick = { onSearch() })
        )

        Spacer(modifier = Modifier.width(10.dp))

        FilterButton(
            text = stringResource(R.string.city_text),
            onFilter = {onCity()},
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(17.dp))

        FilterButton(
            text = stringResource(R.string.price_text),
            onFilter = {onPrice()},
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(10.dp))

        CustomIconButton(
            iconId = R.drawable.cart,
            modifier = Modifier.clickable(onClick = { onCart() }),
            iconSize = 24.dp
        )
    }
}

@Preview
@Composable
private fun MainTopAppBarPrev() {
    MainTopAppBar(
        onSearch = {},
        onCity = {},
        onPrice = {},
        onCart = {}
    )
}