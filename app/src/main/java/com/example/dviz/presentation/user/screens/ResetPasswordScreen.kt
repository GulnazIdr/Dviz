package com.example.dviz.presentation.user.screens

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.user.viewmodel.AuthViewModel
import com.example.dviz.presentation.user.viewmodel.ResetPasswordViewModel
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.inputs.RegistrationField
import com.example.ui_interface.top_bars.TopAppBar
import com.example.ui_interface.user.RegistrTitle
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ResetPasswordScreen(
    navigateToMain: () -> Unit,
    authViewModel: AuthViewModel,
    resetPasswordViewModel: ResetPasswordViewModel,
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    val generatedPassword = resetPasswordViewModel.generatePassword(password)
    val clipData = ClipData.newPlainText(
        stringResource(R.string.copied_text), generatedPassword
    )
    clipData.apply {
        description.extras = PersistableBundle().apply {
            putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
        }
    }
    val clipEntry = ClipEntry(clipData)
    val clipboardManager = LocalClipboard.current

    val snackbarHostState = remember { SnackbarHostState() }
    val authUiResultState by authViewModel.uiResultState.collectAsState()

    val scope = rememberCoroutineScope()

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) {
        authUiResultState.Display(
            onSuccess = {navigateToMain()},
            onChangeButtonState = {},
            snackbarHostState
        )
        Box(
            modifier = modifier.fillMaxSize().background(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 20.dp)
            ) {

                TopAppBar(
                    onBack = {}
                )

                RegistrTitle(
                    registrTitle = stringResource(R.string.reset_pswd_text),
                    registrDescription = stringResource(R.string.enter_new_pswd_text)
                )

                RegistrationField(
                    textValue = password,
                    onValueChanged = { password = it },
                    placeholder = "xxxxxxxx",
                    label = "",
                    errorMessage = authViewModel.passwordErrorHint,
                    isPasswordField = true
                )

                Spacer(modifier = modifier.height(40.dp))

                Row {
                    NavigationButton(
                        stringResource(R.string.generate_pswd_text),
                        onAction = { resetPasswordViewModel.generatePassword(password) },
                        modifier = modifier.weight(1f)
                    )

                    Spacer(modifier = modifier.width(30.dp))

                    NavigationButton(
                        stringResource(R.string.copy_text),
                        onAction = {
                            scope.launch {
                                clipboardManager.setClipEntry(clipEntry)
                            }
                            password = ""
                        },
                        modifier = modifier.weight(1f)
                    )
                }

                Spacer(modifier = modifier.height(40.dp))

                NavigationButton(
                    stringResource(com.example.ui_interface.R.string.update_text),
                    onAction = {
                        authViewModel.updateUserPassword(password)
                    },
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = modifier.height(40.dp))
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Preview
//@Composable
//private fun ResetPasswordScreenPreview() {
//    ResetPasswordScreen(
//        navigateToMain = {}
//    )
//}