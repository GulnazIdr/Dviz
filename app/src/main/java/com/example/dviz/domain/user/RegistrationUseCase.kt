package com.example.dviz.domain.user

import androidx.datastore.core.DataStore
import com.example.dviz.data.user.UserSerial
import com.example.dviz.domain.DataStoreRepository
import com.example.dviz.domain.models.User
import io.github.jan.supabase.auth.auth
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        name: String?,
        email: String?,
        password: String?,
        phone: String?,
        isPolicyChecked: Boolean
    ): RegisterResult{
        val nameResult = ValidationUtil.validateName(name)
        val phoneResult = ValidationUtil.validatePhone(phone)
        val passwordResult = ValidationUtil.validatePassword(password)
        val emailResult = ValidationUtil.validateEmail(email)
        val policyResult = ValidationUtil.validatePolicy(isPolicyChecked)

        if (nameResult.isNotEmpty() || phoneResult.isNotEmpty() || passwordResult.isNotEmpty() ||
            emailResult.isNotEmpty() || policyResult.isNotEmpty()
        )
            return RegisterResult(
                emailResult, passwordResult, nameResult,
                phoneResult, policyResult
            )

        val result =  authRepository.signUp(
            User(
                name!!, phone!!, password!!, email!!
            )
        )

        return RegisterResult(result = result)
    }
}