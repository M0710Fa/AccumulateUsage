package com.example.accumulateusage.model.repository

import android.app.usage.UsageStats

interface UsageRepository {
    suspend fun getUsage(fileName: String): String?
    suspend fun appendUsage(fileName: String, usageStats: List<UsageStats>)
}