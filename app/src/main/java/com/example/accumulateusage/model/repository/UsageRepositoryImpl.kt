package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats
import android.content.Context
import com.example.accumulateusage.model.source.local.UsageFileDataSource
import javax.inject.Inject

class UsageRepositoryImpl @Inject constructor(
    private val usageFileDataSource: UsageFileDataSource,
): UsageRepository {
    override suspend fun getUsage(fileName: String): String? {
        return usageFileDataSource.readFile(fileName)
    }

    override suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>) {
        return usageFileDataSource.appendUsage(fileName, usageStats)
    }
}