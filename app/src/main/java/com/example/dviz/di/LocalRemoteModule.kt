package com.example.dviz.di

import com.example.dviz.data.api.RemoteEventRepoImpl
import com.example.dviz.data.room.LocalEventRepository
import com.example.dviz.domain.EventRepoImpl
import com.example.dviz.domain.event.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalRemoteModule {
    @Provides
    @Singleton
    fun provideEventImpl(
        localEventRepository: LocalEventRepository,
        remoteEventRepository: RemoteEventRepoImpl,
        supabaseClient: SupabaseClient
    ): EventRepository{
        return EventRepoImpl(
            localEventRepository, remoteEventRepository, supabaseClient
        )
    }

}