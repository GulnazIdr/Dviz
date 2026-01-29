package com.example.ui_interface.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.EventUi
import com.example.ui_interface.theme.white

@Composable
fun CardEvent(
    event: EventUi,
    onCard:  (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(213.dp)
            .background(white)
            .clickable(onClick = {onCard(event.id)})
    ) {
        Column {
//            AsyncImage(
//                model = event.image,
//                contentDescription = "${event.title} image",
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//                onError = { }
//            )

            Spacer(modifier.weight(1f))

            Text(
                text = event.title
            )

            Text(
                text = event.price.toString()
            )
        }
    }
}

@Preview
@Composable
private fun CardEventPrev() {
    CardEvent(
        event = EventUi(1, "Клод Моне. Магия воды и света", 1002.3, "", categoryUi = CategoryUi(1, "")),
        onCard = {}
    )
}