package com.example.dviz.di

import com.example.dviz.domain.user.AuthRepository
import com.example.dviz.domain.user.GeneratePasswordUseCase
import com.example.dviz.domain.user.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RegistrationModule {

    @Provides
    @ViewModelScoped
    fun provideRegistrUseCase(authRepository: AuthRepository) = RegistrationUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun provideGenPswdUseCase(): GeneratePasswordUseCase = GeneratePasswordUseCase()
}