package com.example.dviz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://wopdifuhjgomqukouzhb.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndvcGRpZnVoamdvbXF1a291emhiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Njk1NzI1NTcsImV4cCI6MjA4NTE0ODU1N30.pUNwSB9Jxsj1h601WHk9KveOSpCSBUn6xrCO8q_jqO8"
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
            defaultSerializer = KotlinXSerializer()
        }
    }
}