package com.example.dviz.di

import com.example.ui_interface.calendar.CalendarDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class CalendarModule {
    @Provides
    @ViewModelScoped
    fun provideCalendarDataSource(): CalendarDataSource = CalendarDataSource()
}