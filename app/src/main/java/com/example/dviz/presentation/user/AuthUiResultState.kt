package com.example.dviz.presentation.user

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

interface UiResultState  {

    @Composable
    fun Display(
        onSuccess: () -> Unit,
        onChangeButtonState: (Boolean) -> Unit,
        snackbarHostState: SnackbarHostState
    )

    data object Initial: UiResultState{
        @Composable
        override fun Display(
            onSuccess: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState,
        ) {}
    }

    data class Loading ( val message: String ): UiResultState{
        @Composable
        override fun Display(
            onSuccess: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(
                    message = message,
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )
            }
        }

    }

    data object Success: UiResultState{
        @Composable
        override fun Display(
            onSuccess: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            onSuccess()
        }
    }

    data class Error(val message: String) : UiResultState{
        @Composable
        override fun Display(
            onSuccess: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            onChangeButtonState(true)
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}