package com.example.dviz.presentation.events

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.dviz.presentation.CircleLoading
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.black
import com.example.ui_interface.theme.darkBlack
import com.example.ui_interface.theme.white

@Composable
fun CardEvent(
    event: EventUi,
    onCard:  (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(260.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
            .background(white)
            .clickable(onClick = { onCard(event.id) })
    ) {

        Box(
            modifier = Modifier.weight(2f)
        ) {
            SubcomposeAsyncImage(
                model = event.images[0],
                contentDescription = "${event.title} image",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)),
                loading = {
                    Box {
                        CircleLoading()
                    }
                },
                error = {
                    Icon(
                        painter = painterResource(com.example.ui_interface.R.drawable.ic_increment),
                        contentDescription = "error image",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .rotate(90f),
                        tint = black
                    )
                    Log.e(
                        "loading sneakers error",
                        "${it.result.throwable.message} " + "${it.result.throwable.cause}"
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .weight(1.5f)
                .width(180.dp)
                .padding(7.dp)
        ) {
            Text(
                text =
                    if (event.title.length > 50) event.title.take(49) +"..."
                    else event.title,
                style = LocalTypography.current.titleMedium3.copy(
                    color = darkBlack
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box() {
                Text(
                    text = event.price,
                    style = LocalTypography.current.titleMedium3.copy(
                        color = darkBlack,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
        }

    }
}

//@Preview
//@Composable
//private fun CardEventPrev() {
//    CardEvent(
//        event = EventUi(1, "Клод Моне. Магия воды и света", 1002.3, "", categoryUi = CategoryUi(1, "")),
//        onCard = {}
//    )
//}