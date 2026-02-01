package com.example.dviz.domain.user

import com.example.dviz.domain.models.User

interface AuthRepository {
    suspend fun signUp(user: User): AuthResult
    suspend fun signIn(user: User): AuthResult
    suspend fun signOut()
    suspend fun updatePassword(newPassword: String): AuthResult
    suspend fun sendOtp(email: String): AuthResult
    suspend fun checkOtp(email: String, token: String): AuthResult
}