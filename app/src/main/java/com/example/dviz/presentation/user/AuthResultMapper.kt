package com.example.dviz.presentation.user

import com.example.dviz.domain.user.AuthResult
import com.example.dviz.presentation.user.UiResultState
import jakarta.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<UiResultState> {

    override fun mapSuccess(): UiResultState {
        return UiResultState.Success
    }

    override fun mapError(
        errorMessage: String
    ): UiResultState {
        return UiResultState.Error(errorMessage)
    }

}