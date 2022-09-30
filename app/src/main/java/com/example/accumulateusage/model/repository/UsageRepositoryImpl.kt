package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats
import javax.inject.Inject

class UsageRepositoryImpl @Inject constructor(

): UsageRepository {
    override suspend fun getUsage(fileName: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>) {
        TODO("Not yet implemented")
    }
}