package com.example.dviz.domain

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setLoggedInStatus(isLoggedIn: Boolean)
    fun getLoggedInStatus(): Flow<Boolean>

    suspend fun setCurrentUserId(id: String?)
    suspend fun getCurrentUserId(): String
    suspend fun clearUserId()
}