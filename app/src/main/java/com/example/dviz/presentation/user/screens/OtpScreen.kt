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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.user.viewmodel.ResetPasswordViewModel
import com.example.ui_interface.R
import com.example.ui_interface.components.BottomCurle
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.otp.Otp
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.theme.white
import com.example.ui_interface.top_bars.TopAppBar
import com.example.ui_interface.user.RegistrTitle

@Composable
fun OTPScreen(
    resetPasswordViewModel: ResetPasswordViewModel,
    navigateToResetPassword: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var index by remember { mutableIntStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    val authUiResultState = resetPasswordViewModel.authUiResultState.collectAsState().value
    val sentOtpResult = resetPasswordViewModel.sentOtpResult.collectAsState().value

    BackHandler { onBack() }

    CommonScaffold(
        snackBarHostState
    ) {padding ->

        authUiResultState.Display(
            onSuccess = {navigateToResetPassword()},
            onChangeButtonState = {
                isError = it
            },
            snackBarHostState
        )

        sentOtpResult.Display(
            onSuccess = {},
            onChangeButtonState = {},
            snackBarHostState
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(white)
                .padding(padding)
        ) {
            Column(
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TopAppBar(
                        onBack = { onBack() }
                    )

                    RegistrTitle(
                        registrTitle = stringResource(R.string.otp_text),
                        registrDescription = stringResource(R.string.otp_descr)
                    )
                }

                Text(
                    text = stringResource(R.string.otp_code_text),
                    style = LocalTypography.current.captionSemibold2
                )

                Spacer(modifier = Modifier.height(16.dp))

                Otp(
                    value = resetPasswordViewModel.getOtpItem(index),
                    onValueChanged = {
                        resetPasswordViewModel.setOtpItem(
                            it.second, it.first
                        )
                        index = it.first
                    },
                    onResend = {resetPasswordViewModel.resendOtp()},
                    isError = isError
                )

                Spacer(modifier = modifier.height(10.dp))
            }

            BottomCurle()
        }
    }
}

//
//@Preview
//@Composable
//private fun OTPScreenPreview() {
//    OTPScreen(
//        email = "",
//        onBack = {},
//        navigateToResetPassword = {}
//    )
//}