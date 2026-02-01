package com.example.dviz.di

import com.example.dviz.data.api.RemoteEventRepoImpl
import com.example.dviz.domain.event.EventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//interface EventModule
//{
//    @Binds
//    @Singleton
//    fun provideEventRepo(remoteEventRepoImpl: RemoteEventRepoImpl): EventRepository
//}