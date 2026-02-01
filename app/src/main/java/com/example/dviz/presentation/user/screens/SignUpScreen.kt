package com.example.dviz.presentation.user.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dviz.presentation.user.viewmodel.AuthViewModel
import com.example.ui_interface.R
import com.example.ui_interface.buttons.NavigationButton
import com.example.ui_interface.components.CommonScaffold
import com.example.ui_interface.inputs.RegistrationField
import com.example.ui_interface.theme.LocalTypography
import com.example.ui_interface.top_bars.TopAppBar
import com.example.ui_interface.user.BottomRegistrTextNav
import com.example.ui_interface.user.PdfReader
import com.example.ui_interface.user.PolicyRow

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onBackClick: () -> Unit,
    onLogIn: () -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("+7") }
    var checkedState by remember { mutableStateOf(false) }
    val authUiResultState by authViewModel.uiResultState.collectAsState()
    val context = LocalContext.current

    val pdfUrl = "https://drive.google.com/uc?export=download&id=1UD2e1VZKOHcXqrHlH65PBhPAXIftbg07"
    val pdfReader = PdfReader(context)

    val snackbarHostState = remember { SnackbarHostState() }

    authUiResultState.Display(
        onSuccess = {onSignUp()},
        onChangeButtonState = {},
        snackbarHostState = snackbarHostState
    )

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ){ padding ->
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
                    isRegistrationScreen = true,
                    onBack = {onBackClick()}
                )

                Spacer(modifier = Modifier.height(11.dp))

                Text(
                    text = stringResource(R.string.signup_text),
                    style = LocalTypography.current.titleBold
                )

                Spacer(modifier = Modifier.height(66.dp))

                RegistrationField(
                    textValue = name,
                    onValueChanged = {name = it},
                    label = stringResource(R.string.name_label),
                    placeholder = stringResource(R.string.name_placeholder),
                    errorMessage = authViewModel.nameErrorHint
                )
                Spacer(modifier = Modifier.height(12.dp))

                RegistrationField(
                    textValue = email,
                    onValueChanged = {email = it},
                    label = stringResource(R.string.email_label),
                    placeholder = stringResource(R.string.email_placeholder),
                    errorMessage = authViewModel.emailErrorHint
                )

                Spacer(modifier = Modifier.height(30.dp))

                RegistrationField(
                    textValue = password,
                    onValueChanged = {password = it},
                    label = stringResource(R.string.password_label),
                    placeholder = stringResource(R.string.password_placeholder),
                    errorMessage = authViewModel.passwordErrorHint
                )

                Spacer(modifier = Modifier.height(10.dp))

                RegistrationField(
                    textValue = phone,
                    onValueChanged = {phone = it},
                    label = stringResource(R.string.phone_text),
                    placeholder = stringResource(R.string.password_placeholder),
                    errorMessage = authViewModel.phoneErrorHint
                )

                Spacer(modifier = Modifier.height(10.dp))

                PolicyRow(
                    onPdfRead = { pdfReader.readPdf(pdfUrl) },
                    isIconVisible = checkedState,
                    toggleAgreement = {checkedState = !checkedState},
                    errorMessage = authViewModel.policyErrorHint
                )

                Spacer(modifier = Modifier.height(12.dp))

                NavigationButton(
                    text = stringResource(R.string.register_text),
                    onAction = {authViewModel.signUp(
                        name, email, password, phone, checkedState
                    )}
                )

                BottomRegistrTextNav(
                    question = stringResource(R.string.has_account_question),
                    link = stringResource(R.string.login_text),
                    onNavigateTo = { onLogIn() }
                )

            }
        }
    }
}

//@Preview
//@Composable
//private fun SignUpScreenPrev() {
//    SignUpScreen(
//        onBackClick = {},
//        onLogIn = {},
//        onSignUp = {}
//    )
//}