package com.example.dviz.domain.user

data class RegisterResult(
    val emailError: String = "",
    val passwordError: String= "",
    val nameError: String= "",
    val phoneError: String= "",
    val policyError: String= "",
    val result: AuthResult? = null
)
