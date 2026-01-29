package com.example.dviz.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.dviz.domain.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStorePref: DataStore<Preferences>
): DataStoreRepository {
    val LOGGED_IN = booleanPreferencesKey("logginState")
    val USER_ID = stringPreferencesKey("current_user_id")

    override suspend fun setLoggedInStatus(isLoggedIn: Boolean) {
        dataStorePref.edit { pref ->
            pref[booleanPreferencesKey(LOGGED_IN.toString())] = isLoggedIn
        }
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStorePref.data.map { pref ->
            pref[LOGGED_IN] ?: false
        }
    }

    override suspend fun setCurrentUserId(id: String?) {
        dataStorePref.edit { pref ->
            if(id != null) pref[stringPreferencesKey(USER_ID.toString())] = id
        }
    }

    override suspend fun getCurrentUserId(): String {
        return dataStorePref.data.map { pref ->
            pref[USER_ID] ?: ""
        }.first()
    }

    override suspend fun clearUserId() {
        dataStorePref.edit { pref ->
            pref.remove(USER_ID)
        }
    }

}