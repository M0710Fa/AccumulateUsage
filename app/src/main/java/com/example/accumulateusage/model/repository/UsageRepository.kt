package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats
import kotlinx.coroutines.flow.Flow

interface UsageRepository {
    suspend fun getUsage(fileName: String): Flow<List<String>>?
    suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>)
}