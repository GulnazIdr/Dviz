package com.example.dviz.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.dviz.data.DataStoreRepositoryImpl
import com.example.dviz.data.user.UserPreferencesSerializer
import com.example.dviz.data.user.UserSerial
import com.example.dviz.domain.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {context.preferencesDataStoreFile("data_store")}
        )
    }

    @Singleton
    @Provides
    fun provideDatastoreImpl(datastore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(datastore)
    }

    @Singleton
    @Provides
    fun provideCustomDataStore(@ApplicationContext context: Context): DataStore<UserSerial>{
        return DataStoreFactory.create(
            serializer = UserPreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { UserSerial()}
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {context.dataStoreFile("USER_CREDENTIALS")}
        )
    }
}