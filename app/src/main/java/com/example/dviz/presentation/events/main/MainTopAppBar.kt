package com.example.dviz.presentation.events.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.CircleLoading
import com.example.dviz.presentation.events.LocationUi
import com.example.ui_interface.R
import com.example.ui_interface.buttons.CustomIconButton
import com.example.ui_interface.buttons.FilterButton

@Composable
fun MainTopAppBar(
    cityList: List<LocationUi>,
    onSearch: () -> Unit,
    onCity: (String) -> Unit,
    onPrice: () -> Unit,
    onCart: () -> Unit,
    isCityLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column() {
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

//        Box(
//            modifier = Modifier.weight(1f)
//        ) {
            if (!isCityLoading)
                CityDropDownFilter(
                    onCity = { onCity(it) },
                    cityList = cityList,
                )
//            } else
//                CircleLoading()
            // }

            Spacer(modifier = Modifier.width(17.dp))

            FilterButton(
                text = stringResource(R.string.price_text),
                onFilter = { onPrice() },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            CustomIconButton(
                iconId = R.drawable.cart,
                modifier = Modifier.clickable(onClick = { onCart() }),
                iconSize = 24.dp
            )

        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

//@Preview
//@Composable
//private fun MainTopAppBarPrev() {
//    MainTopAppBar(
//        onSearch = {},
//        onCity = {},
//        onPrice = {},
//        onCart = {}
//    )
//}