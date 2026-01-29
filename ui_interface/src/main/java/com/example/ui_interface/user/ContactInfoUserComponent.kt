package com.example.ui_interface.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.ui_interface.R
import com.example.ui_interface.components.IconBoxComponent
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lightgray

@Composable
fun ContactInfoCartComponent(
    id: Int,
    userInfo: String,
    descr: String,
    editEnabled: Boolean = false,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userValue by remember { mutableStateOf(userInfo) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconBoxComponent(
                id = id,
                backgroundColor = lightgray,
                shape = RoundedCornerShape(12.dp),
                size = 40.dp,
                iconSize = 20.dp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                BasicTextField(
                    value = userValue,
                    onValueChange = {userValue = it},
                    textStyle = LocalTypography.current.titleMedium2.copy(
                        lineHeight = 0.2.em
                    ),
                    enabled = editEnabled
                )

                Text(
                    text = descr,
                    style = LocalTypography.current.titleMedium2.copy(
                        lineHeight = 0.16.em
                    ),
                )
            }
        }

        if (!editEnabled)
            IconButton(
                onClick = { onEdit() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = ""
                )
            }
        else
            Icon(
                painter = painterResource(R.drawable.ic_done),
                contentDescription = "",
                tint = lightgray
            )

    }
}