package com.example.ui_interface.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_interface.R
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.lightYellow
import com.example.ui_interface.theme.lightgray
import com.example.ui_interface.theme.red

@Composable
fun RegistrationField(
    textValue: String,
    onValueChanged: (value: String) -> Unit,
    label: String,
    placeholder: String,
    isError: Boolean,
    errorMessage: String,
    isPasswordField: Boolean = false,
    modifier: Modifier = Modifier
) {
    var passwordVisibiliy by remember { mutableStateOf(false) }

    val inputState by animateColorAsState(
        targetValue = if(isError) red else lightYellow,
        label = "input field error"
    )

    Column(
        modifier = modifier
    ){
        Text(
            text = label,
            style = LocalTypography.current.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(2.dp, color = inputState, shape = RoundedCornerShape(14.dp))
                .background(lightYellow),
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (textValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = LocalTypography.current.titleMedium2
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    BasicTextField(
                        value = textValue,
                        textStyle = LocalTypography.current.titleMedium,
                        onValueChange = onValueChanged,
                        visualTransformation = if (isPasswordField && !passwordVisibiliy) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        }
                    )

                    if (isPasswordField) {
                        IconButton(onClick = { passwordVisibiliy = !passwordVisibiliy }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_visibility_off),
                                contentDescription = "password visibility off"
                            )
                        }
                    }
                }
            }
        }

        if(isError)
            Text(
                text = errorMessage,
                style = LocalTypography.current.titleMediumError,
                modifier = modifier
                    .padding(top = 10.dp)
                    .align(Alignment.End)
            )
    }
}

@Preview
@Composable
private fun RegistrationFieldPreview() {
    RegistrationField(
        textValue = "name",
        onValueChanged = {},
        label = "Name",
        placeholder = stringResource(R.string.name_placeholder),
        isError = false,
        errorMessage = "name shouldnt be empty",
        isPasswordField = true,
    )
}