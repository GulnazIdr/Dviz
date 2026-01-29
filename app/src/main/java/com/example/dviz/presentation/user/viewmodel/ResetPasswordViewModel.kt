package com.example.dviz.presentation.user.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dviz.domain.AuthRepository
import com.example.dviz.domain.GeneratePasswordUseCase
import com.example.dviz.presentation.user.AuthResultMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val generatePasswordUseCase: GeneratePasswordUseCase,
    private val authRepository: AuthRepository,
    private val mapper: AuthResultMapper,
) : ViewModel() {
        private val _authUiResultState = MutableStateFlow<
                AuthUiResultState>(AuthUiResultState.Initial)
        val authUiResultState: StateFlow<AuthUiResultState> get() = _authUiResultState.asStateFlow()

        private val _sentOtpResult = MutableStateFlow<
                AuthUiResultState>(AuthUiResultState.Initial)
        val sentOtpResult: StateFlow<AuthUiResultState> get() = _sentOtpResult.asStateFlow()

        private val _modalWindowState = mutableStateOf(false)
        var modalWindowState: State<Boolean> = _modalWindowState

        var email: String = ""
            set(value){
                if(!value.isBlank()) field = value
            }

        val otpList = mutableStateListOf("", "", "", "", "", "")

        fun setOtpItem(item: String, index: Int){
            otpList[index] = item

            if(otpList.all { it.isNotEmpty() }) {
                checkOtp(email, otpList.joinToString(""))
            }
        }
        fun getOtpItem(index: Int): String{
            return otpList[index]
        }

        fun resendOtp(){
            sendOtp(email)
        }

        fun sendOtp(email: String) = viewModelScope.launch {
            _sentOtpResult.value = AuthUiResultState.Loading("Sending..")
            val result = authRepository.sendOtp(email)
            _sentOtpResult.value = result.map(mapper)
        }

        fun checkOtp(email: String, entered: String) = viewModelScope.launch {
            val result = authRepository.checkOtp(email,entered)
            _authUiResultState.value = result.map(mapper)
        }

        fun showSuccessDialog(){
            _modalWindowState.value = true
        }

        fun generatePassword(password: String): String{
            _authUiResultState.value = AuthUiResultState.Loading("Generating..")
            if(password.isNotEmpty()) return generatePasswordUseCase(password)
            else{
                _authUiResultState.value = AuthUiResultState.Loading("Type a text in the field" +
                        " to generate the password")
                return ""
            }
        }

    }