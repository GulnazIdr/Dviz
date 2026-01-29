package com.example.ui_interface.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.buttons.ChangeAmountSideButton
import com.example.ui_interface.buttons.DeleteSideButton
import com.example.ui_interface.components.CardEvent
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.EventUi

@Composable
fun CartItemAction(
    event: EventUi,
    onDelete: (id: Int) -> Unit,
    //onChangeAmount: (amount: Int, delta: Int) -> Unit
) {
    var showOnChangeAmount by remember { mutableStateOf(false) }
    var showDelete by remember { mutableStateOf(false) }

    var amount by remember { mutableStateOf(event.amount) }

    val gestureModifier = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragStart = {showDelete=true},
            onHorizontalDrag = { change, dragAmount ->

                val dragDelta = change.positionChange().x.toInt()
                if (dragDelta > 10) {
                    showOnChangeAmount = true
                    showDelete = false
                } else if (dragDelta < -10) {
                    showDelete = true
                    showOnChangeAmount = false
                }
//                else if(dragDelta < 2){
//                    showDelete = false
//                    showOnChangeAmount = false
//                }

                change.consume()
            }
        )
    }

    Row(modifier = gestureModifier){
        AnimatedVisibility(visible = showOnChangeAmount) {
            ChangeAmountSideButton(
                amount = amount,
                onAddToCart = {
                    amount++
                  //  onChangeAmount(amount, +1)
                },
                onDecrement = {
                    if (amount != 0) amount --;
                   // onChangeAmount(amount, -1)
                }
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                //   .padding(horizontal = 20.dp)
                .padding(end = 20.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            CardEvent(
                event = event,
                onCard = {}
            )
        }

        AnimatedVisibility(visible = showDelete) {
            DeleteSideButton(
                onDelete = {
                    onDelete(event.id)
                }
            )
        }
    }
}

@Preview
@Composable
private fun CartItemActionPrev() {
    CartItemAction(
        event = EventUi(1, "Клод Моне. Магия воды и света", 1002.3, "", categoryUi = CategoryUi(1, "ca")),
        onDelete = {},
     //   onChangeAmount = {}
    )
}