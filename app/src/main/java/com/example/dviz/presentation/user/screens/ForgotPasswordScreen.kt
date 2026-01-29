package com.example.dviz.presentation.user.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.user.viewmodel.AuthViewModel
import com.example.dviz.presentation.user.viewmodel.ResetPasswordViewModel
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.components.BottomCurle
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.inputs.RegistrationField
import com.example.ui_interface.top_bars.TopAppBar
import com.example.ui_interface.user.EmailSentModalWindow
import com.example.ui_interface.user.RegistrTitle

@Composable
fun ForgotPasswordScreen(
    resetPasswordViewModel: ResetPasswordViewModel,
    onBackClick: () -> Unit,
    navigateToOtp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    var modalWindowOpened = resetPasswordViewModel.modalWindowState.value

    val sentOtpResult = resetPasswordViewModel.sentOtpResult.collectAsState().value

    var customModifier =
        if(modalWindowOpened) {
            EmailSentModalWindow(email = email, navigateToOtp = {navigateToOtp()})

            modifier
                .fillMaxSize()
                .background(Color.White)
                .blur(radius = 4.dp)
        }else modifier
            .fillMaxSize()
            .background(Color.White)

    sentOtpResult.Display(
        onSuccess = {resetPasswordViewModel.showSuccessDialog()},
        onChangeButtonState = {},
        snackBarHostState
    )

    BackHandler { onBackClick() }

    CommonScaffold(
        snackBarHostState
    ) { padding ->

        Box(modifier = customModifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                TopAppBar(
                    onBack = { onBackClick() }
                )

                Spacer(modifier = Modifier.height(11.dp))

                RegistrTitle(
                    registrTitle = stringResource(R.string.forgot_pass_text),
                    registrDescription = stringResource(R.string.forgot_pass_descr)
                )

                Spacer(modifier = Modifier.height(40.dp))

                RegistrationField(
                    textValue = email,
                    onValueChanged = {email = it},
                    label = stringResource(R.string.email_label),
                    placeholder = stringResource(R.string.email_placeholder),
                    errorMessage = ""
                )

                Spacer(modifier = modifier.height(40.dp))

                NavigationButton(
                    text = stringResource(R.string.send_text),
                    onAction = {resetPasswordViewModel.sendOtp(email)},
                )
            }

            BottomCurle()
        }
    }
}

//@Preview
//@Composable
//private fun ForgotPasswordPreview() {
//    ForgotPasswordScreen(
//        navigateToOtp = {},
//        onBackClick = {}
//
//    )
//}