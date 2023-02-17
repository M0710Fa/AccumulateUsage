package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats
import com.example.accumulateusage.model.source.local.Usage
import kotlinx.coroutines.flow.Flow

interface UsageRepository {
    suspend fun getUsage(fileName: String): Flow<List<String>>?
    suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>)

    suspend fun getUsages(): Flow<List<Usage>>

    suspend fun addUsage(usage: Usage)
}