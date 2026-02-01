package com.example.dviz.presentation.user.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dviz.domain.user.AuthRepository
import com.example.dviz.domain.user.RegistrationUseCase
import com.example.dviz.domain.models.User
import com.example.dviz.domain.user.ValidationUtil
import com.example.dviz.presentation.user.UiResultState
import com.example.dviz.presentation.user.AuthResultMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResultMapper,
    private val registerUseCase: RegistrationUseCase
) : ViewModel() {
    private val _UiResultState = MutableStateFlow<

            UiResultState>(UiResultState.Initial)
    val uiResultState: StateFlow<UiResultState> get() = _UiResultState.asStateFlow()

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

            if (res.result != null) _UiResultState.value = res.result.map(mapper)
        }
    }

    fun signIn(
        email: String?,
        password: String?
    ) = viewModelScope.launch {
            _UiResultState.value = UiResultState.Loading("Проверка..")
            val result = authRepository.signIn(
                User(
                    email = email.toString(),
                    password = password.toString()
                )
            )
            _UiResultState.value = result.map(mapper)
    }

    fun updateUserPassword(newPassword: String){
        val passwordError = ValidationUtil.validatePassword(newPassword)
        if(passwordError.isEmpty()){
            _UiResultState.value = UiResultState.Loading("Saving..")
            viewModelScope.launch {
                val res = authRepository.updatePassword(newPassword)
                _UiResultState.value = res.map(mapper)
            }
        }else passwordErrorHint = passwordError
    }


    fun signOut() = viewModelScope.launch {
        authRepository.signOut()
    }
}