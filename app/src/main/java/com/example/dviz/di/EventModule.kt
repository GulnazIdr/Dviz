package com.example.dviz.di

import com.example.dviz.data.api.RemoteEventRepoImpl
import com.example.dviz.data.room.LocalEventRepository
import com.example.dviz.data.room.dao.EventDao
import com.example.dviz.data.room.dao.UserDao
import com.example.dviz.domain.DataStoreRepository
import com.example.dviz.domain.event.EventRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteEventModule
{
    @Binds
    @Singleton
    fun provideRemoteEventRepo(remoteEventRepoImpl: RemoteEventRepoImpl): EventRepository
}

@Module
@InstallIn(SingletonComponent::class)
class LocalEventModule
{
    @Provides
    @Singleton
    fun provideLocalEventRepo(
        eventDao: EventDao,
        userDao: UserDao,
        dataStoreRepository: DataStoreRepository
    ): LocalEventRepository{
        return LocalEventRepository(
            eventDao, userDao,dataStoreRepository
        )
    }
}