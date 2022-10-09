package com.example.accumulateusage.model.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsageRepositoryModule {
    @Provides
    fun provideUsageRepository(usageRepositoryImpl: UsageRepositoryImpl): UsageRepository{
        return usageRepositoryImpl
    }
}