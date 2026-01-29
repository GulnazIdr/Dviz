package com.example.dviz.presentation.user.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dviz.data.UserSerial
import com.example.dviz.domain.AuthRepository
import com.example.dviz.domain.RegistrationUseCase
import com.example.dviz.domain.User
import com.example.dviz.domain.ValidationUtil
import com.example.dviz.presentation.user.AuthResultMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResultMapper,
    private val registerUseCase: RegistrationUseCase
) : ViewModel() {
    private val _authUiResultState = MutableStateFlow<

            AuthUiResultState>(AuthUiResultState.Initial)
    val authUiResultState: StateFlow<AuthUiResultState> get() = _authUiResultState.asStateFlow()

    var nameErrorHint by mutableStateOf("")
    var emailErrorHint by  mutableStateOf("")
    var passwordErrorHint by  mutableStateOf("")
    var phoneErrorHint by mutableStateOf("")
    var policyErrorHint by  mutableStateOf("")

    fun signUp(
        name: String?,
        email: String?,
        password: String?,
        phone: String?,
        isPolicyChecked: Boolean
    ){
       // _authUiResultState.value = AuthUiResultState.Loading("Проверка...")
        viewModelScope.launch {
            val res = registerUseCase(name, email, password, phone, isPolicyChecked)
            nameErrorHint = res.nameError
            emailErrorHint = res.emailError
            phoneErrorHint = res.phoneError
            passwordErrorHint = res.passwordError
            policyErrorHint = res.policyError

            if (res.result != null) _authUiResultState.value = res.result.map(mapper)
        }
    }

    fun signIn(
        email: String?,
        password: String?
    ) = viewModelScope.launch {
            _authUiResultState.value = AuthUiResultState.Loading("Проверка..")
            val result = authRepository.signIn(
                User(
                    email = email.toString(),
                    password = password.toString()
                )
            )
            _authUiResultState.value = result.map(mapper)
    }

    fun updateUserPassword(newPassword: String){
        val passwordError = ValidationUtil.validatePassword(newPassword)
        if(passwordError.isEmpty()){
            _authUiResultState.value = AuthUiResultState.Loading("Saving..")
            viewModelScope.launch {
                val res = authRepository.updatePassword(newPassword)
                _authUiResultState.value = res.map(mapper)
            }
        }else passwordErrorHint = passwordError
    }


    fun signOut() = viewModelScope.launch {
        authRepository.signOut()
    }
}