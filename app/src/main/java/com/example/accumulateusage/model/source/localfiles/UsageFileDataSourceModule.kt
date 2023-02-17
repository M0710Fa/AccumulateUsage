package com.example.accumulateusage.model.source.localfiles

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsageFileDataSourceModule {
    @Provides
    fun provideUsageFileDataSource(usageFileDataSourceImpl: UsageFileDataSourceImpl): UsageFileDataSource{
        return usageFileDataSourceImpl
    }
}