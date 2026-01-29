package com.example.dviz.domain

interface AuthResult {
    interface Mapper <T> {
        fun mapSuccess(): T
        fun mapError(errorMessage: String) :T
    }

    fun <T> map (mapper: Mapper<T>): T

    object Success: AuthResult{
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess()
        }
    }

    class Error(val errorMessage: String): AuthResult{
        //var message =
        override fun <T> map(mapper: Mapper<T>): T{
            val message = if (errorMessage.contains("email_address_invalid")) "Неправильная форма почты"
            else if (errorMessage.contains("over_email_send_rate_limit")) "Слишком много попыток. Попробуйте позже еще раз"
            else if (errorMessage.contains("invalid_credentials")) "Неправильная почта или пароль"
            else errorMessage //"Такой аккаунт уже существует"

            return mapper.mapError(message)
        }
    }

}