package com.example.ui_interface.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.ui_interface.R
import com.example.ui_interface.models.OrderUi
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.white

@Composable
fun CartUserInfoComponent(
    orderUi: OrderUi,
    modifier: Modifier = Modifier
) {
    var editEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .padding(start = 20.dp, top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.contact_info_text),
            style = LocalTypography.current.titleMedium2.copy(
                lineHeight = 0.2.em
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoCartComponent(
            id = R.drawable.ic_mail,
            userInfo = orderUi.userUi.email,
            descr = stringResource(R.string.email_label),
            editEnabled = editEnabled,
            onEdit = {editEnabled = true}
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoCartComponent(
            id = R.drawable.ic_phone,
            userInfo = orderUi.userUi.phone,
            descr = stringResource(R.string.phone_text),
            editEnabled = editEnabled,
            onEdit = {editEnabled = true}
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.address_text),
            style = LocalTypography.current.titleMedium2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = orderUi.address,
            style = LocalTypography.current.titleMedium4
        )
    }
}