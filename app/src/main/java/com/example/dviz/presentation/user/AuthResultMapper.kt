package com.example.dviz.presentation.user

import com.example.dviz.domain.AuthResult
import com.example.dviz.presentation.user.viewmodel.AuthUiResultState
import jakarta.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<AuthUiResultState> {

    override fun mapSuccess(): AuthUiResultState {
        return AuthUiResultState.Success
    }

    override fun mapError(
        errorMessage: String
    ): AuthUiResultState {
        return AuthUiResultState.Error(errorMessage)
    }

}