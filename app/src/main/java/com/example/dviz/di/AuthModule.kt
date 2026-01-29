package com.example.dviz.di

import com.example.dviz.data.AuthRepositoryImpl
import com.example.dviz.domain.AuthRepository
import com.example.dviz.domain.AuthResult
import com.example.dviz.domain.GeneratePasswordUseCase
import com.example.dviz.presentation.user.AuthResultMapper
import com.example.dviz.presentation.user.viewmodel.AuthUiResultState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {

    @Binds
    @ViewModelScoped
    fun provideAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    fun provideAuthMapper(authResultMapper: AuthResultMapper): AuthResult.Mapper<AuthUiResultState>
}