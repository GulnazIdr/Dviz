package com.example.dviz.di

import com.example.dviz.data.user.AuthRepositoryImpl
import com.example.dviz.domain.user.AuthRepository
import com.example.dviz.domain.user.AuthResult
import com.example.dviz.presentation.user.AuthResultMapper
import com.example.dviz.presentation.user.UiResultState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {

    @Binds
    @ViewModelScoped
    fun provideAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    fun provideAuthMapper(authResultMapper: AuthResultMapper): AuthResult.Mapper<UiResultState>
}