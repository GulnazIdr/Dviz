package com.example.dviz.data

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.dviz.domain.AuthRepository
import com.example.dviz.domain.AuthResult
import com.example.dviz.domain.DataStoreRepository
import com.example.dviz.domain.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val dataStore: DataStore<UserSerial>,
    private val commonDataStore: DataStoreRepository
): AuthRepository {
    override suspend fun signUp(
        user: User
    ): AuthResult {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                email = user.email
                password = user.password
                data = buildJsonObject {
                    put("name", user.name)
                    put("phone", user.phone)
                }
            }

            dataStore.updateData { userCredentials ->
                userCredentials.copy(
                    name = user.name,
                    password = user.password
                )
            }

            signIn(user)
            AuthResult.Success
        } catch (e: AuthRestException) {
            Log.e("SIGN UP", "${e::class.simpleName} ${e.message}")
            AuthResult.Error(e.message.toString())
        } catch (e: Exception) {
            Log.e("SIGN UP", "failed: ${e::class.simpleName} ${e.message}")
            AuthResult.Error("Что-то пошло не так")
        }
    }

    override suspend fun signIn(user: User): AuthResult {
        return try {
            supabaseClient.auth.signInWith(Email) {
                email = user.email
                password = user.password
            }
            AuthResult.Success
        } catch (e: AuthRestException) {
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error(e.message.toString())
        } catch (e: Exception) {
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error("")
        }
    }

    override suspend fun signOut() {
        try {
            commonDataStore.clearUserId()
            supabaseClient.auth.signOut()
        } catch (e: Exception) {
            Log.e("SIGN OUT", "Something went wrong: ${e::class.simpleName} ${e.message}")
        }
    }

    override suspend fun updatePassword(newPassword: String): AuthResult {
        return try {
            supabaseClient.auth.updateUser {
                password = newPassword
            }
            AuthResult.Success
        } catch (e: Exception) {
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error("")
        }
    }

    override suspend fun sendOtp(email: String): AuthResult {
        return try {
            supabaseClient.auth.resetPasswordForEmail(email = email)
            AuthResult.Success
        } catch (e: AuthRestException) {
            AuthResult.Error("")
        } catch (e: Exception) {
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error("")
        }
    }

    override suspend fun checkOtp(
        email: String,
        token: String
    ): AuthResult {
        return try {
            supabaseClient.auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = email,
                token = token
            )
            AuthResult.Success
        } catch (e: Exception) {
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error("")
        }
    }
}