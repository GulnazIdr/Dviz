package com.example.dviz.presentation.user.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.user.viewmodel.AuthViewModel
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.user.BottomRegistrTextNav
import com.example.ui_interface.inputs.RegistrationField
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.top_bars.TopAppBar

@Composable
fun SignInScreen(
    authViewModel: AuthViewModel,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authUiResultState by authViewModel.authUiResultState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    authUiResultState.Display(
        onSuccess = {onSignIn()},
        onChangeButtonState = {
          //  isButtonEnabled = !it
                              },
        snackbarHostState = snackbarHostState
    )

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                TopAppBar(
                    isLogin = true,
                    isRegistrationScreen = true
                )

                Spacer(modifier = Modifier.height(11.dp))

                Text(
                    text = stringResource(R.string.login_text),
                    style = LocalTypography.current.titleBold
                )

                Spacer(modifier = Modifier.height(66.dp))

                RegistrationField(
                    textValue = email,
                    onValueChanged = { email = it },
                    label = stringResource(R.string.email_label),
                    placeholder = stringResource(R.string.email_placeholder),
                    errorMessage = ""
                )

                Spacer(modifier = Modifier.height(30.dp))

                RegistrationField(
                    textValue = password,
                    onValueChanged = { password = it },
                    label = stringResource(R.string.password_label),
                    placeholder = stringResource(R.string.password_placeholder),
                    errorMessage = ""
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    onClick = {onForgotPassword()},
                    modifier = modifier.align(Alignment.End)
                ) {
                    Text(
                        text = stringResource(R.string.restore_password_text),
                        style = LocalTypography.current.headLineRegular
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                NavigationButton(
                    text = stringResource(R.string.login_text),
                    onAction = {authViewModel.signIn(email, password)}
                )

                BottomRegistrTextNav(
                    question = stringResource(R.string.new_user_question),
                    link = stringResource(R.string.create_user_text),
                    onNavigateTo = {onSignUp()}
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun SignInScreenPrev() {
//    SignInScreen(
//
//    )
//}