package com.example.dviz.domain

import java.security.Policy

object ValidationUtil {
    private const val EMTPY_FIELD_ERROR =  "Заполните поле"

    fun validatePassword(password: String?): String{
        val errorList = listOf(
            "Password must have at least 8 characters",
            "Password must have lowercase letters and digits",
            "Password must have uppercase letter",
            "Password must have symbols"
        )
        if (password.isNullOrBlank()) return EMTPY_FIELD_ERROR

        val noLetterDigit = !password.any{it.isLetterOrDigit()}
        val noUpper = !password.any{it.isUpperCase()}
        val notEnoughChars = password.length < 8
        val passwordIsBlank = password.isBlank()

        val regexSymbol = Regex(".*[!@#$%^&*()+=?~`,./{}].*")
        val noSymbol = !password.matches(regexSymbol)

        val errorMessage = when{
            passwordIsBlank -> errorList[0]
            noLetterDigit -> errorList[1]
            noUpper -> errorList[2]
            noSymbol -> errorList[3]
            notEnoughChars -> errorList[0]
            else -> ""
        }

        if(passwordIsBlank || notEnoughChars || noUpper || noLetterDigit || noSymbol)
            return errorMessage

        return ""
    }

    fun validateEmail(email: String?): String{
        if (email.isNullOrBlank()) return EMTPY_FIELD_ERROR

        val isCorrect = Regex(
            "^[a-z0-9._]+@[a-z]+\\.[a-z]{2,}\$"
        ).matches(email)

        return if (isCorrect) ""
        else "Неправильная форма почты"
    }

    fun validateName(name: String?): String{
        return if (name.isNullOrBlank()) EMTPY_FIELD_ERROR
        else ""
    }

    fun validatePhone(phone: String?): String{
        return if (phone.isNullOrBlank()) EMTPY_FIELD_ERROR
        else ""
    }

    fun validatePolicy(isChecked: Boolean): String{
        return if (isChecked) ""
        else "Прочтите политику конфиденциальности"
    }
}