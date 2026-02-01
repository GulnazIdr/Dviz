package com.example.dviz.di

import android.content.Context
import androidx.room.Room
import com.example.dviz.data.api.EventApi
import com.example.dviz.data.room.DvizDatabase
import com.example.dviz.data.room.dao.EventDao
import com.example.dviz.data.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRetrofit(): EventApi {
        return Retrofit.Builder()
            .baseUrl("https://kudago.com/public-api/v1.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DvizDatabase{
        return Room.databaseBuilder(context, DvizDatabase::class.java, "dviz_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(dvizDatabase: DvizDatabase): EventDao{
        return dvizDatabase.getEventDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(dvizDatabase: DvizDatabase): UserDao{
        return dvizDatabase.getUserDao()
    }
}